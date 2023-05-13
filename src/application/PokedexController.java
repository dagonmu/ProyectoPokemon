package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import crud.PokemonCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Pokemon;
import modelo.Tipo;
import javafx.scene.control.TableColumn;

public class PokedexController implements Initializable{
	@FXML
	private TableView<Pokemon> pokedex;
	@FXML
	private TableColumn<Pokemon, Integer> numPokedex;
	@FXML
	private TableColumn<Pokemon, String> nombre;
	@FXML
	private TableColumn<Pokemon, Tipo> tipo1;
	@FXML
	private TableColumn<Pokemon, Tipo> tipo2;
	@FXML
	private Button btnSalir;
	
	private ObservableList<Pokemon> listaPokedex;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		listaPokedex = FXCollections.observableArrayList(PokemonCRUD.selectPokedex());
		
		//listaPokedex.add(p); Cargar la Pokedex
		
		pokedex.setItems(listaPokedex);
		
		this.numPokedex.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("numPokedex"));
		this.nombre.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("nombre"));
		this.tipo1.setCellValueFactory(new PropertyValueFactory<Pokemon, Tipo>("tipoPrimario"));
		this.tipo2.setCellValueFactory(new PropertyValueFactory<Pokemon, Tipo>("tipoSecundario"));
		
		
	}

	// Event Listener on Button[#btnSalir].onAction
	/**Cierra esta ventana.
	 * @param event
	 */
	@FXML
	public void salir(ActionEvent event) {
		Stage stage = (Stage) this.btnSalir.getScene().getWindow();
		stage.close();
	}

	
}
