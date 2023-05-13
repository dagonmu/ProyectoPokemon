package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Entrenador;
import modelo.Objeto;
import modelo.Pokemon;
import modelo.Tienda;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import crud.ObjetoCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class TiendaController implements Initializable {

	@FXML
	private Button btnComprar;
	@FXML
	private Button btnVender;
	@FXML
	private Button btnSalir;
	@FXML
	private TextArea txtDescripcion;
	@FXML
	private TextField txtPokedollar;
	@FXML
	private TableView<Objeto> tblTienda;
	@FXML
	private TableColumn<Objeto, String> nombre;
	@FXML
	private TableColumn<Objeto, Integer> precioCompra;
	@FXML
	private TableColumn<Objeto, Integer> precioVenta;

	private ObservableList tienda;
	
	private MediaPlayer mediaPlayer;

	private Objeto o;

	private Entrenador e;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		LinkedList<Objeto> objetos = ObjetoCRUD.todosObjetos();
		tienda = FXCollections.observableArrayList(objetos);

		// tienda.add(); Cargar lista objetos con base de datos

		tblTienda.setItems(tienda);

		this.nombre.setCellValueFactory(new PropertyValueFactory<Objeto, String>("nombre"));
		this.precioCompra.setCellValueFactory(new PropertyValueFactory<Objeto, Integer>("precioCompra"));
		this.precioVenta.setCellValueFactory(new PropertyValueFactory<Objeto, Integer>("precioVenta"));

	}

	public void initAttributes(Entrenador e) {
		this.e = e;
		this.txtPokedollar.setText(Integer.toString(e.getPokeDollar()));
		
		String path = "resources/sounds/tienda.wav";
		File archivo = new File(path);
		Media media = new Media(archivo.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}

	/**
	 * Si tienes seleccionado un Objeto del TableView este es copiado en el Objeto
	 * o. Si el valor del atributo pokedollar del objeto Entrenador es mayor o igual
	 * que el atributo precioCompra del Objeto utiliza el metodo static comprar de
	 * Tienda. En caso de no tener Objeto seleccionado o no tener suficientes
	 * pokedollar salta un mensaje.
	 * 
	 * @param event
	 */
	@FXML
	public void comprar(ActionEvent event) {
		o = this.tblTienda.getSelectionModel().getSelectedItem();

		if (o != null) {
			if (o.getPrecioCompra() <= e.getPokeDollar()) {
				Tienda.comprar(e, o);
				ObjetoCRUD.agregarObjetoMochila(e, o);

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Informacion");
				alert.setContentText("Has comprado: " + o.getNombre());
				alert.showAndWait();

				ObjetoCRUD.comprarObjetoPokedollar(e, o);
				this.txtPokedollar.setText(Integer.toString(e.getPokeDollar()));

			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Informacion");
				alert.setContentText("No tienes sufucientes pokedollares");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("Debes seleccionar un objeto");
			alert.showAndWait();
		}

	}

	/**
	 * Si tienes seleccionado un Objeto del TableView este es copiado en el Objeto
	 * o. Si el valor del atributo mochila del objeto Entrenador contiene dicho
	 * Objeto se usa el metodo static vender de Tienda. En caso de no tener Objeto
	 * seleccionado o no tener el Objeto salta un mensaje.
	 * 
	 * @param event
	 */
	@FXML
	public void vender(ActionEvent event) {
		Objeto o = this.tblTienda.getSelectionModel().getSelectedItem();

		if (o != null) {
			if (e.getMochila().contains(o)) {
				Tienda.vender(e, o);
				ObjetoCRUD.quitarObjetoMochila(e, o);

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Informacion");
				alert.setContentText("Has vendido: " + o.getNombre());
				alert.showAndWait();

				ObjetoCRUD.venderObjetoPokedollar(e, o);
				this.txtPokedollar.setText(Integer.toString(e.getPokeDollar()));

			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Informacion");
				alert.setContentText("No tienes este objeto");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("Debes seleccionar un objeto");
			alert.showAndWait();
		}

	}

	/**
	 * Cierra la ventana
	 * 
	 * @param event
	 */
	@FXML
	public void salir(ActionEvent event) {
		
		mediaPlayer.stop();

		Stage stage = (Stage) this.btnSalir.getScene().getWindow();
		stage.close();

	}

	/**
	 * Modifica el contenido del TextArea txtDescripcion con el atributo descripcion
	 * del Objeto seleccionado.
	 * 
	 */
	@FXML
	public void seleccionar() {
		Objeto o = this.tblTienda.getSelectionModel().getSelectedItem();
		try {
			this.txtDescripcion.setText(o.getNombre() + ": " + o.getDescripcion());
		} catch (NullPointerException e) {

		}

	}

}
