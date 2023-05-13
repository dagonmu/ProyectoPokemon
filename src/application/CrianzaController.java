package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import crud.EntrenadorCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Entrenador;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Tipo;
import javafx.scene.control.TableColumn;

public class CrianzaController implements Initializable {
	@FXML
	private TableView<Pokemon> tblEquipo;
	@FXML
	private TableColumn<Pokemon, String> nombreEquipo;
	@FXML
	private TableColumn<Pokemon, String> moteEquipo;
	@FXML
	private TableColumn<Pokemon, Character> sexo;
	@FXML
	private TableColumn<Pokemon, Tipo> tipo1;
	@FXML
	private TableColumn<Pokemon, Tipo> tipo2;
	@FXML
	private TableColumn<Pokemon, Integer> fertilidad;
	@FXML
	private TableView<Pokemon> tblSeleccionados;
	@FXML
	private TableColumn<Pokemon, String> nombreSeleccionado;
	@FXML
	private TableColumn<Pokemon, String> moteSeleccionado;
	@FXML
	private TextField txtPokedollar;
	@FXML
	private Button btnAparear;
	@FXML
	private Button btnSalir;
	@FXML
	private Button btnSeleccionar;
	@FXML
	private Button btnQuitar;

	private Entrenador e;

	private Pokemon p;

	private LinkedList<Pokemon> padres;

	private ObservableList equipo;
	private ObservableList seleccion;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.nombreEquipo.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("nombre"));
		this.moteEquipo.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("mote"));
		this.sexo.setCellValueFactory(new PropertyValueFactory<Pokemon, Character>("sexo"));
		this.tipo1.setCellValueFactory(new PropertyValueFactory<Pokemon, Tipo>("tipoPrimario"));
		this.tipo2.setCellValueFactory(new PropertyValueFactory<Pokemon, Tipo>("tipoSecundario"));
		this.fertilidad.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("fertilidad"));

		this.nombreSeleccionado.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("nombre"));
		this.moteSeleccionado.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("mote"));

	}

	/**
	 * Inicia los atributos.
	 * 
	 * @param e
	 */
	public void initAttributes(Entrenador e) {
		this.e = e;
		this.txtPokedollar.setText(Integer.toString(e.getPokeDollar()));

		padres = new LinkedList<Pokemon>();
		equipo = FXCollections.observableArrayList(e.getEquipo());
		seleccion = FXCollections.observableArrayList();

		// equipo.add(p);
		// Añadir equipo de la base de datos
		tblEquipo.setItems(equipo);
		tblSeleccionados.setItems(seleccion);
	}

	// Event Listener on Button[#btnAparear].onAction
	/**
	 * Si en el TableView elegidos hay 2 Pokemon del sexo opuesto, con fertilidad
	 * mayor a 0 y el Entrenador tiene suficientes pokedollar, se utiliza el metodo
	 * criar() de Entrenador y se añade el Pokemon hijo a la caja del Entrenador.
	 * 
	 * @param event
	 */
	@FXML
	public void aparear(ActionEvent event) {

		Pokemon hijo;

		if ((padres.get(0).getSexo() == 'H' && padres.get(1).getSexo() == 'M')
				|| (padres.get(0).getSexo() == 'M' && padres.get(1).getSexo() == 'H')) {
			if (e.getPokeDollar() - ((padres.get(0).getNivel() + padres.get(1).getNivel()) * 100) >= 0) {
				if (padres.get(0).getFertilidad() > 0 && padres.get(1).getFertilidad() > 0) {

					hijo = e.criar(padres.get(0), padres.get(1));
					e.getCaja().add(hijo);
					EntrenadorCRUD.insertCrianza(hijo, e);
					EntrenadorCRUD.actualizarPokedollar(e);
					tblEquipo.refresh();

					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Informacion");
					alert.setContentText("Crianza realizada con exito. El pokemon fue enviado a la caja.");
					alert.showAndWait();

					this.txtPokedollar.setText(Integer.toString(e.getPokeDollar()));

				} else {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Informacion");
					alert.setContentText("Uno de los pokemon no tiene puntos de fertilidad");
					alert.showAndWait();
				}

			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Informacion");
				alert.setContentText("No tienes suficientes pokedollars");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("Los pokemon seleccionados deben ser del sexo opuesto");
			alert.showAndWait();
		}

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

	// Event Listener on Button[#btnSeleccionar].onAction
	/**
	 * Si hay un registro seleccionado del TableView tblEquipo y se agrega al
	 * TableView tblSeleccionados.
	 * 
	 * @param event
	 */
	@FXML
	public void seleccionar(ActionEvent event) {

		this.p = this.tblEquipo.getSelectionModel().getSelectedItem();
		if (this.p != null) {
			if (!seleccion.contains(this.p)) {
				seleccion.add(this.p);
				padres.add(this.p);
				tblSeleccionados.refresh();
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Informacion");
				alert.setContentText("Este pokemon ya esta seleccionado");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("Debes seleccionar un pokemon del equipo");
			alert.showAndWait();
		}

	}

	/**
	 * Si hay un registro seleccionado del TableView tblSeleccionados se elimina de
	 * este.
	 * 
	 * @param event
	 */
	@FXML
	public void quitar(ActionEvent event) {
		this.p = this.tblSeleccionados.getSelectionModel().getSelectedItem();

		if (this.p != null) {
			seleccion.remove(this.p);
			padres.remove(this.p);
			tblSeleccionados.refresh();
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("Debes seleccionar un pokemon que quitar");
			alert.showAndWait();
		}

	}

}
