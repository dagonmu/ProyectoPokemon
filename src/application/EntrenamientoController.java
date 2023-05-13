package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import crud.EntrenadorCRUD;
import crud.EntrenamientoCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.Entrenador;
import modelo.Pokemon;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class EntrenamientoController implements Initializable {
	@FXML
	private TableView<Pokemon> tblPokemon;
	@FXML
	private TableColumn<Pokemon, String> nombre;
	@FXML
	private TableColumn<Pokemon, String> mote;
	@FXML
	private Button btnFurioso;
	@FXML
	private Button btnPesado;
	@FXML
	private Button btnFuncional;
	@FXML
	private Button btnOnirico;
	@FXML
	private Button btnSalir;
	@FXML
	private TextArea txtArea;
	@FXML
	private TextField txtPokedollar;

	private Entrenador e;

	private Pokemon p;

	private ObservableList equipo;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.nombre.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("nombre"));
		this.mote.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("mote"));

	}

	/**
	 * Inicia los atributos de Entrenador, txtPokedollar, equipo y tblPokemon.
	 * 
	 * @param e
	 */
	public void initAttributes(Entrenador e) {
		this.e = e;
		txtPokedollar.setText(Integer.toString(e.getPokeDollar()));

		equipo = FXCollections.observableArrayList(e.getEquipo());

		tblPokemon.setItems(equipo);
	}

	// Event Listener on Button[#btnFurioso].onAction
	/**
	 * Si hay seleccionado un Pokemon del TableView copia este en el Pokemon p. Si
	 * el valor del atirbuto pokedollar es mayor o igual al coste del entrenamiento
	 * se utiliza el metodo entrenamientoFurioso() de Entrenador.
	 * 
	 * @param event
	 */
	@FXML
	public void furioso(ActionEvent event) {

		p = this.tblPokemon.getSelectionModel().getSelectedItem();

		if (p == null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("Debes seleccionar un pokemon");
			alert.showAndWait();
			return;
		}

		if (e.getPokeDollar() >= (30 * p.getNivel())) {

			e.entrenamientoFurioso(e.getEquipo().get(e.getEquipo().indexOf(p)));
			EntrenamientoCRUD.updateEntrenamientoFurioso(p);
			EntrenadorCRUD.actualizarPokedollar(e);

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("Entrenamiento furioso realizado con exito");
			alert.showAndWait();

			this.txtPokedollar.setText(Integer.toString(e.getPokeDollar()));

		} else {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("No tienes sufucientes pokedollares");
			alert.showAndWait();
		}
	}

	// Event Listener on Button[#btnFurioso].onMouseEntered
	/**
	 * Al pasar el raton sobre el boton btnFurioso modifica el contenido del
	 * TextArea.
	 * 
	 * @param event
	 */
	@FXML
	public void mostrarInfoFurioso(MouseEvent event) {
		this.txtArea.setText("Aumenta 5 puntos:\nAtaque\nAtaque especial\nVelocidad");
	}

	// Event Listener on Button[#btnFurioso].onMouseExited
	/**
	 * Al sacar el raton del boton btnFurioso modifica el contenido del TextArea.
	 * 
	 * @param event
	 */
	@FXML
	public void borrarInfoFurioso(MouseEvent event) {
		this.txtArea.setText("");
	}

	// Event Listener on Button[#btnPesado].onAction
	/**
	 * Si hay seleccionado un Pokemon del TableView copia este en el Pokemon p. Si
	 * el valor del atirbuto pokedollar es mayor o igual al coste del entrenamiento
	 * se utiliza el metodo entrenamientoPesado() de Entrenador.
	 * 
	 * @param event
	 */
	@FXML
	public void pesado(ActionEvent event) {

		p = this.tblPokemon.getSelectionModel().getSelectedItem();

		if (p == null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("Debes seleccionar un pokemon");
			alert.showAndWait();
			return;
		}

		if (e.getPokeDollar() >= (20 * p.getNivel())) {

			e.entrenamientoPesado(e.getEquipo().get(e.getEquipo().indexOf(p)));
			EntrenamientoCRUD.updateEntrenamientoPesado(p);
			EntrenadorCRUD.actualizarPokedollar(e);
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("Entrenamiento pesado realizado con exito");
			alert.showAndWait();

			this.txtPokedollar.setText(Integer.toString(e.getPokeDollar()));

		} else {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("No tienes sufucientes pokedollares");
			alert.showAndWait();
		}
	}

	// Event Listener on Button[#btnPesado].onMouseEntered
	/**
	 * Al pasar el raton sobre el boton modifica el contenido del TextArea.
	 * 
	 * @param event
	 */
	@FXML
	public void mostrarInfoPesado(MouseEvent event) {
		this.txtArea.setText("Aumenta 5 puntos:\nDefensa\nDefensa especial\nVitalidad");
	}

	// Event Listener on Button[#btnPesado].onMouseExited
	/**
	 * Al sacar el raton del boton btnPesado modifica el contenido del TextArea.
	 * 
	 * @param event
	 */
	@FXML
	public void borrarInfoPesado(MouseEvent event) {
		this.txtArea.setText("");
	}

	// Event Listener on Button[#btnFuncional].onAction
	/**
	 * Si hay seleccionado un Pokemon del TableView copia este en el Pokemon p. Si
	 * el valor del atirbuto pokedollar es mayor o igual al coste del entrenamiento
	 * se utiliza el metodo entrenamientoFuncional() de Entrenador.
	 * 
	 * @param event
	 */
	@FXML
	public void funcional(ActionEvent event) {

		p = this.tblPokemon.getSelectionModel().getSelectedItem();

		if (p == null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("Debes seleccionar un pokemon");
			alert.showAndWait();
			return;
		}

		if (e.getPokeDollar() >= (40 * p.getNivel())) {

			e.entrenamientoFuncional(e.getEquipo().get(e.getEquipo().indexOf(p)));
			EntrenamientoCRUD.updateEntrenamientoFuncional(p);
			EntrenadorCRUD.actualizarPokedollar(e);
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("Entrenamiento funcional realizado con exito");
			alert.showAndWait();

			this.txtPokedollar.setText(Integer.toString(e.getPokeDollar()));

		} else {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("No tienes sufucientes pokedollares");
			alert.showAndWait();

		}
	}

	// Event Listener on Button[#btnFuncional].onMouseEntered
	/**
	 * Al pasar el raton sobre el boton btnFuncional modifica el contenido del
	 * TextArea.
	 * 
	 * @param event
	 */
	@FXML
	public void mostrarInfoFuncional(MouseEvent event) {
		this.txtArea.setText("Aumenta 5 puntos:\nAtaque\nDefensa\nVelocidad\nVitalidad");
	}

	// Event Listener on Button[#btnFuncional].onMouseExited
	/**
	 * Al sacar el raton del boton btnFuncional modifica el contenido del TextArea.
	 * 
	 * @param event
	 */
	@FXML
	public void borrarInfoFuncional(MouseEvent event) {
		this.txtArea.setText("");
	}

	// Event Listener on Button[#btnOnirico].onAction
	/**
	 * Si hay seleccionado un Pokemon del TableView copia este en el Pokemon p. Si
	 * el valor del atirbuto pokedollar es mayor o igual al coste del entrenamiento
	 * se utiliza el metodo entrenamientoOnirico() de Entrenador.
	 * 
	 * @param event
	 */
	@FXML
	public void onirico(ActionEvent event) {

		p = this.tblPokemon.getSelectionModel().getSelectedItem();

		if (p == null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("Debes seleccionar un pokemon");
			alert.showAndWait();
			return;
		}

		if (e.getPokeDollar() >= (40 * p.getNivel())) {

			e.entrenamientoOnirico(e.getEquipo().get(e.getEquipo().indexOf(p)));
			EntrenamientoCRUD.updateEntrenamientoOnirico(p);
			EntrenadorCRUD.actualizarPokedollar(e);
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("Entrenamiento onirico realizado con exito");
			alert.showAndWait();

			this.txtPokedollar.setText(Integer.toString(e.getPokeDollar()));

		} else {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("No tienes sufucientes pokedollares");
			alert.showAndWait();
		}
	}

	// Event Listener on Button[#btnOnirico].onMouseEntered
	/**
	 * Al pasar el raton sobre el boton btnOnirico modifica el contenido del
	 * TextArea.
	 * 
	 * @param event
	 */
	@FXML
	public void mostrarInfoOnirico(MouseEvent event) {
		this.txtArea.setText("Aumenta 5 puntos:\nAtaque especial\nDefensa especial\nVelocidad\nVitalidad");
	}

	// Event Listener on Button[#btnOnirico].onMouseExited
	/**
	 * Al sacar el raton del boton btnOnirico modifica el contenido del TextArea.
	 * 
	 * @param event
	 */
	@FXML
	public void borrarInfoOnirico(MouseEvent event) {
		this.txtArea.setText("");
	}

	// Event Listener on Button[#btnSalir].onAction
	/**
	 * Cierra la ventana.
	 * 
	 * @param event
	 */
	@FXML
	public void salir(ActionEvent event) {
		Stage stage = (Stage) this.btnSalir.getScene().getWindow();
		stage.close();
	}

}
