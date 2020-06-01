import retrofit.JsonPlaceholderApi;
import retrofit.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class AddNoteClass extends JPanel {

    private JsonPlaceholderApi jsonPlaceholderApi;

    JLabel addTitleLabel, addNoteLabel;
    JTextField addTitleTextField, addNoteTextField;
    JButton sendButton;

    public AddNoteClass(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jPanel.setPreferredSize(new Dimension(400, 400));
        jPanel.setBackground(MyColor.WHITE);
        jPanel.setSize(400, 400);
        jPanel.setBackground(MyColor.PRIMARY_COLOR);

        addTitleLabel = new JLabel("Add Title");
        addTitleLabel.setForeground(MyColor.WHITE);
        jPanel.add(addTitleLabel);

        addTitleTextField = new JTextField();
        jPanel.add(addTitleTextField);

        addNoteLabel = new JLabel("Add Note");
        addNoteLabel.setForeground(MyColor.WHITE);
        jPanel.add(addNoteLabel);

        addNoteTextField = new JTextField();
        jPanel.add(addNoteTextField);

        sendButton = new JButton("Send");
        sendButton.addActionListener(e -> {

            SignUpClass.mainPanel.removeAll();
            SignUpClass.mainPanel.repaint();
            SignUpClass.mainPanel.revalidate();

            SignUpClass.mainPanel.add(new NoteClass());
            SignUpClass.mainPanel.repaint();
            SignUpClass.mainPanel.revalidate();

            addNote();
        });
        jPanel.add(sendButton);

        add(jPanel);

        setupRetrofit();
    }

    private void addNote(){
        String uuid = UUID.randomUUID().toString();

        Call<String> callPutNote = jsonPlaceholderApi.putNote(User.getUserUID(), uuid, addNoteTextField.getText());
        callPutNote.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

        Call<String> callPutTitle = jsonPlaceholderApi.putTitle(User.getUserUID(), uuid, addTitleTextField.getText());
        callPutTitle.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

        Call<String> callPutUUID = jsonPlaceholderApi.putUUID(User.getUserUID(), uuid, uuid);
        callPutUUID.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void setupRetrofit(){
        final String baseUrl = "https://notebook-346f1.firebaseio.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);
    }

}
