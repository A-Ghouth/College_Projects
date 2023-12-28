
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class JavaFXApplication1 extends Application
{

    TextField keywordTextField;
    TextField fileNameTextField;
    private MonoCipher mcipher;
    private VCipher vcipher;

    private boolean stop = false; // To stop Loop in readchar
    private boolean Work = false; // To print Successful of the program ?
    private char Last;         // Check for the filename last char
    private int nextChar = 0; // Next char to read

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        layoutComponents(primaryStage);
    }

    private void layoutComponents(Stage primaryStage)
    {

        Label keywordLabel = new Label("KEYWORD");
        this.keywordTextField = new TextField();

        Label messageLabel = new Label("FILE NAME");
        this.fileNameTextField = new TextField();

        Button btnCaesar = new Button("Process mono cipher");
        btnCaesar.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
////////////////////////////////////////////////////////////////////////////////   // BUTTON Mono
            public void handle(ActionEvent arg0)
            {
                if (getKeyword() == true && processFileName() == true)
                {
                    mcipher = new MonoCipher(keywordTextField.getText());
                    try
                    {
                        processFile(false);
                        showAlertMessage(done(Work));
                    } catch (IOException ex)
                    {
                        System.out.println("Erorr in BUTTON Mono");
                    }

                    System.exit(0);
                }

            }
////////////////////////////////////////////////////////////////////////////////
        });

        Button btnVigenere = new Button("Process vigenere cipher");
        btnVigenere.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
////////////////////////////////////////////////////////////////////////////////   // BUTTON Vigenere
            public void handle(ActionEvent arg0)
            {
                ///keyword///
                if (getKeyword() == true && processFileName() == true)
                {
                    vcipher = new VCipher(keywordTextField.getText());
                    try
                    {
                        processFile(true);
                        showAlertMessage(done(Work));
                    } catch (IOException ex)
                    {
                        System.out.println("Erorr in BUTTON Vigenere");
                    }
                    System.exit(0);
                }

            }
////////////////////////////////////////////////////////////////////////////////
        });

        GridPane root = new GridPane();
        root.setHgap(20);
        root.setVgap(20);

        root.setAlignment(Pos.CENTER);

        root.addRow(0, keywordLabel, keywordTextField);
        root.addRow(1, messageLabel, fileNameTextField);
        root.addRow(2, btnCaesar, btnVigenere);

        Scene scene = new Scene(root, 350, 150);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Encryption/Decryption program");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
///////////////////////////////////////////////////////////////////////////////   //KeyWord        

    private boolean getKeyword()
    {
        String text = this.keywordTextField.getText();
        boolean dup = false;
        for (int i = 0; i < text.length(); i++)
        {
            for (int j = i + 1; j < text.length(); j++)
            {
                if (text.charAt(i) == text.charAt(j))
                {
                    dup = true;
                    break;
                }
            }
        }
        if (text.equals(text.toUpperCase()) && text.isEmpty() == false && dup == false)
        {
            return true;
        } else if (text.equals(text.toUpperCase()) == false)
        {

            showAlertMessage("The input of keyword must be capitalized");
            clearAndRequestFocusForTextField(true);
            return false;
        } else if (text.isEmpty() == true)
        {
            showAlertMessage("The input of keyword must be not empty");
            clearAndRequestFocusForTextField(true);
            return false;
        } else if (dup == true)
        {
            showAlertMessage("The input of keyword must be without duplicated characters");
            clearAndRequestFocusForTextField(true);
            return false;
        } else
        {
            return false;
        }
    }
////////////////////////////////////////////////////////////////////////////////   //FileName     

    private boolean processFileName()
    {
        String filename = this.fileNameTextField.getText();
        if (filename.isEmpty() == false)
        {
            Last = filename.charAt(filename.length() - 1);
            if (Last == 'P' || Last == 'C')
            {
                return true;
            } else
            {
                showAlertMessage("The last character of file name must be capital P  or  C");
                clearAndRequestFocusForTextField(false);
                return false;
            }
        } else
        {
            showAlertMessage("filename field must not be empty");
            clearAndRequestFocusForTextField(false);
            return false;
        }
    }
////////////////////////////////////////////////////////////////////////////////   //File

    private boolean processFile(boolean c) throws IOException
    {
        String x = "";

        if (c == false)
        {
            if (Last == 'P')
            {
                while (stop == false)
                {
                    x = x + String.valueOf(mcipher.encode(readChar()));
                }
            }
            if (Last == 'C')
            {
                while (stop == false)
                {
                    x = x + String.valueOf(mcipher.decode(readChar()));
                }
            }
        }
        if (c == true)
        {
            if (Last == 'P')
            {
                while (stop == false)
                {
                    x = x + String.valueOf(vcipher.encode(readChar()));
                }
            }
            if (Last == 'C')
            {
                while (stop == false)
                {
                    x = x + String.valueOf(vcipher.decode(readChar()));
                }
            }
        }
        writFile(x);
        return Work;

    }
////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////   //Methods

    public char readChar() throws IOException
    {
        stop = true;
        int counter = 0;
        char x = ' ';
        try (FileReader read = new FileReader(fileNameTextField.getText() + ".txt"))
        {
            while ((read.ready()))
            {
                x = (char) read.read();
                if (counter == nextChar)
                {
                    if (read.ready() == true)
                    {
                        stop = false;
                    }
                    nextChar++;
                    break;
                }
                counter++;
            }
        } catch (FileNotFoundException e)
        {
            showAlertMessage("            File NOT Found" + "\n"
                    + "  Operations Were NOT Successful ");
            System.exit(0);
        }
        return x;
    }
////////////////////////////////////////////////////////////////////////////////        

    public void writFile(String x) throws IOException
    {
        char lastChar;
        if (Last == 'P')
        {
            lastChar = 'C';
        } else
        {
            lastChar = 'D';
        }

        FileWriter witeFile;
        String fileName = "";

        for (int j = 0; j < fileNameTextField.getText().length() - 1; j++)
        {
            fileName = fileName + fileNameTextField.getText().charAt(j);
        }
        try
        {
            witeFile = new FileWriter(fileName + lastChar + ".txt");
            witeFile.write(x);
            witeFile.close();
            Work = true;
        } catch (FileNotFoundException e)
        {
            System.out.println("Erorr in write file");

        }

    }

////////////////////////////////////////////////////////////////////////////////
    public String done(boolean x)
    {
        String success = "operations were NOT successful ";
        if (Work == true)
        {
            success = "         Operations Were Successful ";
        }
        return success;
    }
////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////

    private void showAlertMessage(String message)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearAndRequestFocusForTextField(boolean isKeyword)
    {
        if (isKeyword)
        {
            this.keywordTextField.clear();
            this.keywordTextField.requestFocus();
        } else
        {
            this.fileNameTextField.clear();
            this.fileNameTextField.requestFocus();
        }
    }
////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
}
