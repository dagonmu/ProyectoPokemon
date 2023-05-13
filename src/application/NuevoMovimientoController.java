package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import crud.MovimientoCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Combate;
import modelo.Entrenador;
import modelo.Movimiento;
import modelo.Pokemon;
import modelo.Tipo;
import javafx.scene.control.TableColumn;

public class NuevoMovimientoController implements Initializable {
	@FXML
	private TableView<Movimiento> tblMovimientos;
	@FXML
	private TableColumn<Movimiento, String> nombre;
	@FXML
	private TableColumn<Movimiento, Tipo> tipo;
	@FXML
	private TableColumn<Movimiento, Integer> potencia;
	@FXML
	private TableColumn<Movimiento, String> estado;
	@FXML
	private TableColumn<Movimiento, String> mejora;
	@FXML
	private TextArea txtMovimiento;
	@FXML
	private Button btnSustituir;
	@FXML
	private Button btnOlvidar;

	private Pokemon p;

	private Entrenador e;

	private Movimiento nuevoMovimiento;

	private Movimiento movimientoElegido;

	private ObservableList movimientos;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		LinkedList<Movimiento> listaMov = MovimientoCRUD.todosMovimientos();
		nuevoMovimiento = listaMov
				.get((int) Math.floor(Math.random() * (0 - (listaMov.size() - 1)) + (listaMov.size() - 1)));

		txtMovimiento.setText("Intentando aprender: " + nuevoMovimiento.getNombre());

		this.nombre.setCellValueFactory(new PropertyValueFactory<Movimiento, String>("nombre"));
		this.tipo.setCellValueFactory(new PropertyValueFactory<Movimiento, Tipo>("tipoMovimiento"));
		this.potencia.setCellValueFactory(new PropertyValueFactory<Movimiento, Integer>("potencia"));
		this.estado.setCellValueFactory(new PropertyValueFactory<Movimiento, String>("estado"));
		this.mejora.setCellValueFactory(new PropertyValueFactory<Movimiento, String>("mejora"));
	}

	/**
	 * Inicia atributos.
	 * 
	 * @param p
	 * @param e
	 */
	public void initAttributes(Pokemon p, Entrenador e) {
		this.p = p;
		this.e = e;
		movimientos = FXCollections.observableArrayList(p.getMovimientos());

		tblMovimientos.setItems(movimientos);
	}

	// Event Listener on Button[#btnSustituir].onAction
	/**
	 * Inicia el atributo de movimientoElegido con el valor del registro
	 * seleccionado de tblMovimientos y lo modifica con el valor de nuevoMovimiento.
	 * 
	 * @param event
	 */
	@FXML
	public void sustituir(ActionEvent event) {
		movimientoElegido = this.tblMovimientos.getSelectionModel().getSelectedItem();

		if (movimientoElegido != null) {
			for (int i = 0; i < p.getMovimientos().size(); i++) {
				if (p.getMovimientos().get(i).getNombre().equals(movimientoElegido.getNombre())) {
					e.getEquipo().get(i).getMovimientos().set(i, nuevoMovimiento);
				}
			}

			MovimientoCRUD.updateAprenderMovimiento(nuevoMovimiento.getNombre(), movimientoElegido.getNombre(), p);

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText(p.getNombre() + " aprendió: " + nuevoMovimiento.getNombre());
			alert.showAndWait();
			
			Combate.logs((p.getNombre() + " aprendió: " + nuevoMovimiento.getNombre()));

			Stage stage = (Stage) this.btnSustituir.getScene().getWindow();
			stage.close();
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("Seleccione un movimiento");
			alert.showAndWait();
		}
	}

	// Event Listener on Button[#btnOlvidar].onAction
	/**
	 * Cierra la ventana.
	 * 
	 * @param event
	 */
	@FXML
	public void olvidar(ActionEvent event) {

		Stage stage = (Stage) this.btnOlvidar.getScene().getWindow();
		stage.close();
	}

}
