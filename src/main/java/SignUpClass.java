import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import retrofit.JsonPlaceholderApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SignUpClass extends JFrame {

    JsonPlaceholderApi jsonPlaceholderApi;

    Color primaryColor = new Color(49, 67, 91);
    Color secondaryColor = new Color(174, 190, 206);

    JPanel mainPanel;
    JLabel emailLabel, passwordLabel;
    JTextField emailTextField, passwordTextField;
    JButton signInButton, signUpButton;

    public SignUpClass() {

        setLayout(null);
        setPreferredSize(new Dimension(500, 500));
        setTitle("Notebook");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setVisible(true);

        emailLabel = new JLabel("Email: ");
        passwordLabel = new JLabel("Password: ");
        emailTextField = new JTextField("efsaneemail@gmail.com");
        emailTextField.setMaximumSize(new Dimension(1000, 20));
        passwordTextField = new JTextField("123456");
        passwordTextField.setMaximumSize(new Dimension(1000, 20));
        signInButton = new JButton("Sign In");
        signUpButton = new JButton("Sign Up");

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setPreferredSize(new Dimension(500, 500));
        mainPanel.setVisible(true);
        mainPanel.setBackground(secondaryColor);

        mainPanel.add(emailLabel);
        mainPanel.add(emailTextField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordTextField);
        mainPanel.add(signUpButton);
        mainPanel.add(signInButton);
        add(mainPanel);

//        NoteClass noteClass = new NoteClass();
//        add(noteClass);

        requestFocus();
        pack();

        //RETROFIT
        final String baseUrl = "https://www.googleapis.com/identitytoolkit/v3/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);

        String payload = "{email:\"" + emailTextField.getText() + "\",password:\"" + passwordTextField.getText() + "\",returnSecureToken:true}";

        JsonObject jsonObject = new JsonParser().parse(payload).getAsJsonObject();
        //


        signUpButton.addActionListener(e -> {
            Call<JsonObject> callSignUp = jsonPlaceholderApi.signUp(jsonObject);
            callSignUp.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (!response.isSuccessful()) {
                        System.out.println(response.errorBody());
                    } else {
                        JsonObject object = new Gson().fromJson(response.body(), JsonObject.class);

                        System.out.println("Hesap Oluşturuldu");

                        object.get("email").getAsString();
                        object.get("idToken").getAsString();
                        object.get("localId").getAsString();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    System.out.println(t.getLocalizedMessage());
                }
            });
        });

        signInButton.addActionListener(e -> {
            Call<JsonObject> callSignIn = jsonPlaceholderApi.signIn(jsonObject);
            callSignIn.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (!response.isSuccessful()) {
                        System.out.println(response.errorBody());
                    } else {
                        JsonObject object = new Gson().fromJson(response.body(), JsonObject.class);

                        System.out.println("Giriş Yapıldı");

                        System.out.println(object.get("idToken").getAsString());
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

}

