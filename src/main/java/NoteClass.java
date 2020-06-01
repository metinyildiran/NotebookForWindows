import com.google.gson.JsonObject;
import retrofit.JsonPlaceholderApi;
import retrofit.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.io.IOException;

public class NoteClass extends JPanel {

    private JsonPlaceholderApi jsonPlaceholderApi;

    JPanel jPanel;
    DefaultListModel<String> model;
    JList<String> jList;
    JButton addNoteButton;

    public NoteClass() {
        jPanel = new JPanel();
        //jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jPanel.setPreferredSize(new Dimension(500, 500));
        jPanel.setBackground(MyColor.WHITE);
        jPanel.setSize(500, 500);
        jPanel.setBackground(MyColor.PRIMARY_COLOR);

        model = new DefaultListModel<>();
        jList = new JList<>();
        jList.setPreferredSize(new Dimension(400, 400));
        jList.setSize(400, 400);
        jPanel.add(jList);

        addNoteButton = new JButton("Add Note");
        addNoteButton.setBackground(MyColor.BUTTON_COLOR);
        addNoteButton.addActionListener(e -> {
            SignUpClass.mainPanel.removeAll();
            SignUpClass.mainPanel.repaint();
            SignUpClass.mainPanel.revalidate();

            SignUpClass.mainPanel.add(new AddNoteClass());
            SignUpClass.mainPanel.repaint();
            SignUpClass.mainPanel.revalidate();
        });

        jPanel.add(addNoteButton);

        add(jPanel);

        setupRetrofit();

        model.addListDataListener(new ListDataListener() {
            @Override
            public void intervalAdded(ListDataEvent e) {
                System.out.println("intervalAdded");
//                jList.updateUI();
            }

            @Override
            public void intervalRemoved(ListDataEvent e) {

            }

            @Override
            public void contentsChanged(ListDataEvent e) {
                System.out.println("contentsChanged");
            }
        });

        jList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public void setForeground(Color fg) {
                super.setForeground(Color.WHITE);
            }
        });

        jList.addListSelectionListener(e -> {
//            jList.updateUI();
//            jPanel.repaint();
//            jPanel.revalidate();
            getNotes();
        });

        getNotes();
        jList.setModel(model);
    }

    private synchronized void getNotes() {

        Model.notes.clear();
        Model.titles.clear();
        Model.uuids.clear();

        model.removeAllElements();
        jList.removeAll();
        jPanel.repaint();
        jPanel.revalidate();

        Call<JsonObject> callGetDatabase = jsonPlaceholderApi.getDatabase(User.getUserUID());
        callGetDatabase.enqueue(new Callback<>() {
            @Override
            public synchronized void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (!response.isSuccessful()) {
                    if (response.errorBody() != null) {
                        try {
                            System.out.println("getDatabase: " + response.errorBody().string());
                        } catch (IOException ioException) {
                            System.out.print("getDatabase catch: ");
                            ioException.printStackTrace();
                        }
                    }
                } else {
                    JsonObject allDatabase = response.body();

                    Object[] uuids = new Object[0];
                    if (allDatabase != null) {
                        uuids = allDatabase.keySet().toArray();
                    }

                    JsonObject body = null;
                    for (Object uuid : uuids) {
                        if (response.body() != null) {
                            body = (JsonObject) response.body().get(uuid.toString());
                        }

                        if (body != null) {
                            try {
                                String singleNote = body.get("note").getAsString();
                                String singleTitle = body.get("title").getAsString();
                                String singleUUID = body.get("uuid").getAsString();

//                                System.out.println(Model.getNotes());
//                                System.out.println(Model.getTitles());
//                                System.out.println(Model.getUuids());

                                System.out.println("Model.getSize(): " + Model.getSize());
                                SignUpClass.model.update(singleNote, singleTitle, singleUUID);
                                model.addElement(singleNote);

                                jList.updateUI();

                            } catch (NullPointerException nullPointerException) {
                                nullPointerException.printStackTrace();
                            }
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                System.out.println("getDatabase onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    private void setupRetrofit() {
        final String baseUrl = "https://notebook-346f1.firebaseio.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);
    }
}
