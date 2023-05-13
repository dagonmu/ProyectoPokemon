package application;

import java.io.IOException;
import java.sql.SQLException;

import crud.ConexionSQL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * @author Daniel González Muñoz
 * @author Pablo Meroño López
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		try {
			ConexionSQL.getConexion();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/vistas/LoginEntrenador.fxml"));
			// Cargo la ventana
			Pane ventana = (Pane) loader.load();

			// Cargo el scene
			Scene scene = new Scene(ventana);

			// Seteo la scene y la muestro
			primaryStage.setTitle("POKEMON");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
