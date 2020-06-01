import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import retrofit.JsonPlaceholderApi;
import retrofit.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class SignUpClass extends JFrame {

    private JsonPlaceholderApi jsonPlaceholderApi;

    static JPanel mainPanel;
    JLabel emailLabel, passwordLabel;
    JTextField emailTextField, passwordTextField;
    JButton signInButton, signUpButton;

    public static Model model;

    public SignUpClass() {
        model = new Model();

        setPreferredSize(new Dimension(500, 500));
        setTitle("Notebook");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setVisible(true);

        emailLabel = new JLabel("Email: ");
        emailLabel.setForeground(MyColor.WHITE);
        passwordLabel = new JLabel("Password: ");
        passwordLabel.setForeground(MyColor.WHITE);
        emailTextField = new JTextField("efsaneemail@gmail.com");
        emailTextField.setMaximumSize(new Dimension(1000, 20));
        passwordTextField = new JTextField("123456");
        passwordTextField.setMaximumSize(new Dimension(1000, 20));
        signInButton = new JButton("Sign In");
        signInButton.setBackground(MyColor.BUTTON_COLOR);
        signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(MyColor.BUTTON_COLOR);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setPreferredSize(new Dimension(500, 500));
        mainPanel.setVisible(true);
        mainPanel.setBackground(MyColor.PRIMARY_COLOR);

        mainPanel.add(emailLabel);
        mainPanel.add(emailTextField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordTextField);
        mainPanel.add(signUpButton);
        mainPanel.add(signInButton);
        add(mainPanel);

        requestFocus();
        pack();

        setupRetrofit();

        signUpButton.addActionListener(e -> {
            String payload = "{email:\"" + emailTextField.getText() + "\",password:\"" + passwordTextField.getText() + "\",returnSecureToken:true}";

            JsonObject jsonObject = new JsonParser().parse(payload).getAsJsonObject();

            Call<JsonObject> callSignUp = jsonPlaceholderApi.signUp(jsonObject);
            callSignUp.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (!response.isSuccessful()) {
                        if (response.errorBody() != null) {
                            try {
                                System.out.println(response.errorBody().string());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    } else {
                        JsonObject object = new Gson().fromJson(response.body(), JsonObject.class);

                        System.out.println("Hesap Oluşturuldu");

                        User.setEmail(object.get("email").getAsString());
                        User.setIdToken(object.get("idToken").getAsString());
                        User.setUserUID(object.get("localId").getAsString());

                        mainPanel.removeAll();
                        mainPanel.repaint();
                        mainPanel.revalidate();

                        mainPanel.add(new NoteClass());
                        mainPanel.repaint();
                        mainPanel.revalidate();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    System.out.println(t.getLocalizedMessage());
                }
            });
        });

        signInButton.addActionListener(e -> {
            String payload = "{email:\"" + emailTextField.getText() + "\",password:\"" + passwordTextField.getText() + "\",returnSecureToken:true}";

            JsonObject jsonObject = new JsonParser().parse(payload).getAsJsonObject();

            Call<JsonObject> callSignIn = jsonPlaceholderApi.signIn(jsonObject);
            callSignIn.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (!response.isSuccessful()) {
                        if (response.errorBody() != null) {
                            try {
                                System.out.println(response.errorBody().string());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    } else {
                        JsonObject object = new Gson().fromJson(response.body(), JsonObject.class);

                        System.out.println("Giriş Yapıldı");
                        System.out.println(object.get("idToken").getAsString());

                        User.setEmail(object.get("email").getAsString());
                        User.setIdToken(object.get("idToken").getAsString());
                        User.setUserUID(object.get("localId").getAsString());

                        mainPanel.removeAll();
                        mainPanel.repaint();
                        mainPanel.revalidate();

                        mainPanel.add(new NoteClass());
                        mainPanel.repaint();
                        mainPanel.revalidate();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    System.out.println(t.getLocalizedMessage());
                }
            });
        });
    }

    public static void main(String[] args) {
        SignUpClass signUpClass = new SignUpClass();
        signUpClass.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void setupRetrofit(){
        final String baseUrl = "https://www.googleapis.com/identitytoolkit/v3/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);
    }
}
