package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import crud.CajaEquipoCRUD;
import crud.EntrenadorCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Entrenador;
import modelo.Pokemon;
import modelo.Tipo;
import javafx.scene.control.TableColumn;

public class EquipoCajaController implements Initializable {
	@FXML
	private TableView<Pokemon> tblEquipo;
	@FXML
	private TableColumn<Pokemon, String> nombreEquipo;
	@FXML
	private TableColumn<Pokemon, String> moteEquipo;
	@FXML
	private TableColumn<Pokemon, Integer> nivelEquipo;
	@FXML
	private TableColumn<Pokemon, Tipo> tipo1Equipo;
	@FXML
	private TableColumn<Pokemon, Tipo> tipo2Equipo;
	@FXML
	private TableColumn<Pokemon, Integer> vitalidadEquipo;
	@FXML
	private TableColumn<Pokemon, Integer> ataqueEquipo;
	@FXML
	private TableColumn<Pokemon, Integer> ataqueEspEquipo;
	@FXML
	private TableColumn<Pokemon, Integer> defensaEquipo;
	@FXML
	private TableColumn<Pokemon, Integer> defensaEspEquipo;
	@FXML
	private TableColumn<Pokemon, Integer> velocidadEquipo;
	@FXML
	private TableColumn<Pokemon, Integer> estaminaEquipo;
	@FXML
	private TableColumn<Pokemon, Integer> fertilidadEquipo;
	@FXML
	private TableColumn<Pokemon, Character> sexoEquipo;
	@FXML
	private Button btnSalir;
	@FXML
	private Button btnAgregar;
	@FXML
	private TableView<Pokemon> tblCaja;
	@FXML
	private TableColumn<Pokemon, String> nombreCaja;
	@FXML
	private TableColumn<Pokemon, String> moteCaja;
	@FXML
	private TableColumn<Pokemon, Integer> nivelCaja;
	@FXML
	private TableColumn<Pokemon, Tipo> tipo1Caja;
	@FXML
	private TableColumn<Pokemon, Tipo> tipo2Caja;
	@FXML
	private TableColumn<Pokemon, Integer> vitalidadCaja;
	@FXML
	private TableColumn<Pokemon, Integer> ataqueCaja;
	@FXML
	private TableColumn<Pokemon, Integer> ataqueEspCaja;
	@FXML
	private TableColumn<Pokemon, Integer> defensaCaja;
	@FXML
	private TableColumn<Pokemon, Integer> defensaEspCaja;
	@FXML
	private TableColumn<Pokemon, Integer> velocidadCaja;
	@FXML
	private TableColumn<Pokemon, Integer> estaminaCaja;
	@FXML
	private TableColumn<Pokemon, Integer> fertilidadCaja;
	@FXML
	private TableColumn<Pokemon, Character> sexoCaja;

	@FXML
	private Label lblEquipo;
	@FXML
	private Label lblCaja;
	@FXML
	private Button btnQuitar;

	private Entrenador e;

	private ObservableList<Pokemon> equipo;

	private ObservableList<Pokemon> caja;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.nombreEquipo.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("nombre"));
		this.moteEquipo.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("mote"));
		this.nivelEquipo.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("nivel"));
		this.tipo1Equipo.setCellValueFactory(new PropertyValueFactory<Pokemon, Tipo>("tipoPrimario"));
		this.tipo2Equipo.setCellValueFactory(new PropertyValueFactory<Pokemon, Tipo>("tipoSecundario"));
		this.vitalidadEquipo.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("vitalidad"));
		this.ataqueEquipo.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("ataque"));
		this.ataqueEspEquipo.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("ataqueEspecial"));
		this.defensaEquipo.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("defensa"));
		this.defensaEspEquipo.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("defensaEspecial"));
		this.velocidadEquipo.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("velocidad"));
		this.estaminaEquipo.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("estamina"));
		this.fertilidadEquipo.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("fertilidad"));
		this.sexoEquipo.setCellValueFactory(new PropertyValueFactory<Pokemon, Character>("sexo"));

		this.nombreCaja.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("nombre"));
		this.moteCaja.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("mote"));
		this.nivelCaja.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("nivel"));
		this.tipo1Caja.setCellValueFactory(new PropertyValueFactory<Pokemon, Tipo>("tipoPrimario"));
		this.tipo2Caja.setCellValueFactory(new PropertyValueFactory<Pokemon, Tipo>("tipoSecundario"));
		this.vitalidadCaja.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("vitalidad"));
		this.ataqueCaja.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("ataque"));
		this.ataqueEspCaja.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("ataqueEspecial"));
		this.defensaCaja.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("defensa"));
		this.defensaEspCaja.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("defensaEspecial"));
		this.velocidadCaja.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("velocidad"));
		this.estaminaCaja.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("estamina"));
		this.fertilidadCaja.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("fertilidad"));
		this.sexoCaja.setCellValueFactory(new PropertyValueFactory<Pokemon, Character>("sexo"));

	}

	/**
	 * Inicia los atributos.
	 * 
	 * @param e
	 */
	public void initAttributes(Entrenador e) {
		this.e = e;

		equipo = FXCollections.observableArrayList(e.getEquipo());
		caja = FXCollections.observableArrayList(e.getCaja());

		tblEquipo.setItems(equipo);
		tblCaja.setItems(caja);
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

	// Event Listener on Button[#btnAgregar].onAction
	/**
	 * Si hay un registro seleccionado de tblCaja, este se copia a un objeto
	 * Pokemon. Si el equipo de Entrenador tiene menos de 6 Pokemon, se le agrega el
	 * Pokemon seleccionado y se elimina de tblCaja y de la caja del Entrenador.
	 * 
	 * @param event
	 */
	@FXML
	public void agregar(ActionEvent event) {
		Pokemon p = this.tblCaja.getSelectionModel().getSelectedItem();

		if (p == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Debes seleccionar un pokemon de la caja");
			alert.showAndWait();
		} else if (e.getEquipo().size() < 6) {
			for (int i = 0; i < e.getCaja().size(); i++) {
				if (e.getCaja().get(i).getIdPokemon() == p.getIdPokemon()) {
					equipo.add(p);
					e.getEquipo().add(p);
					caja.remove(i);
					e.getCaja().remove(i);
					CajaEquipoCRUD.updateEquipo(p, e);
				}
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No puedes tener mas de 6 pokemons en tu equipo");
			alert.showAndWait();
		}
		tblEquipo.refresh();
		tblCaja.refresh();
	}

	// Event Listener on Button[#btnQuitar].onAction
	/**
	 * Si hay un registro seleccionado de tblEquipo, este se copia a un objeto
	 * Pokemon. Si el equipo de Entrenador tiene 2 o mas Pokemon, se elimina el
	 * Pokemon seleccionado y se agrega a tblCaja y a la caja de Entrenador.
	 * 
	 * @param event
	 */
	@FXML
	public void quitar(ActionEvent event) {
		Pokemon p = this.tblEquipo.getSelectionModel().getSelectedItem();

		if (p == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Debes seleccionar un pokemon de la caja");
			alert.showAndWait();

		} else if (e.getEquipo().size() >= 2) {
			for (int i = 0; i < e.getEquipo().size(); i++) {
				if (e.getEquipo().get(i).getIdPokemon() == p.getIdPokemon()) {
					equipo.remove(i);
					e.getEquipo().remove(i);
					caja.add(p);
					e.getCaja().add(p);
					CajaEquipoCRUD.updateCaja(p, e);
				}
			}

		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No puedes tener menos de 1 pokemon en tu equipo");
			alert.showAndWait();
		}
		tblEquipo.refresh();
		tblCaja.refresh();
	}

}
