package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import crud.CombateCRUD;
import crud.EntrenadorCRUD;
import crud.PokemonCRUD;
import javafx.event.ActionEvent;

import javafx.scene.control.ProgressBar;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Combate;
import modelo.Entrenador;
import modelo.Estado;
import modelo.Pokemon;
import modelo.Turno;
import modelo.Movimiento;
import modelo.Tipo;
import javafx.scene.control.Label;

public class CombateController implements Initializable {
	@FXML
	private Button btnMov1;
	@FXML
	private Button btnMov2;
	@FXML
	private Button btnMov3;
	@FXML
	private Button btnMov4;
	@FXML
	private Button btnDescansar;
	@FXML
	private Button btnCambiarPokemon;
	@FXML
	private Button btnHuir;
	@FXML
	private ProgressBar pbRival;
	@FXML
	private ProgressBar pbJugador;
	@FXML
	private Label lblNombrePokemon;
	@FXML
	private Label lblNombrePokemonRival;
	@FXML
	private Label lblEntrenador;
	@FXML
	private Label lblRival;
	@FXML
	private ImageView imgPokemonPropio;
	@FXML
	private ImageView imgPokemonRival;

	private MediaPlayer mediaPlayer;

	private Entrenador e;

	private Combate combate;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		String path = "resources/sounds/batalla.wav";
		File archivo = new File(path);
		Media media = new Media(archivo.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
		
	}

	/**
	 * Inicia atributos del controlador
	 * 
	 * @param e
	 */
	public void initAttributes(Entrenador e) {
		combate = new Combate();

		this.e = e;

		Entrenador entrenador = e = EntrenadorCRUD.selectSacarEntrenador(e.getNombre());

		combate.setEntrenador(entrenador);
		combate.setPokemonEntrenador(entrenador.getEquipo().get(0));
		crearEntrenadorRival();

		try {
			File file = new File(combate.getPokemonEntrenador().getImgTrasera());
			Image image = new Image(file.toURI().toString());
			imgPokemonPropio.setImage(image);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			File file = new File(combate.getPokemonEntrenadorRival().getImgFrontal());
			Image image = new Image(file.toURI().toString());
			imgPokemonRival.setImage(image);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		setTextoBotones();
		setTextoLabels();
		lblEntrenador.setText(e.getNombre());
		lblRival.setText(combate.getEntrenadorRival().getNombre());
		
		Combate.logs("--Comienzo del combate--");

	}

	/**
	 * Obtiene la lista completa de Entrenadores de la base de datos y elige uno
	 * aleatorio. Modifica los atributos entrenadorRival y pokemonEntrenadorRival de
	 * combate.
	 * 
	 */
	public void crearEntrenadorRival() {
		LinkedList<String> entrenadores = EntrenadorCRUD.selectEntrenadores();
		String rival = entrenadores
				.get((int) Math.floor(Math.random() * (0 - (entrenadores.size())) + (entrenadores.size())));
		combate.setEntrenadorRival(EntrenadorCRUD.selectSacarEntrenador(rival));
		combate.setPokemonEntrenadorRival(combate.getEntrenadorRival().getEquipo().get(0));
	}

	/**
	 * Modifica el progreso de ambas ProgressBar en relacion a la vitalidadActual de
	 * los pokemon en combate.
	 */
	public void actualizarProgressBar() {
		// Barra de vida Jugador
		this.pbJugador.setProgress((double) combate.getPokemonEntrenador().getVitalidadActual() * 100
				/ (double) combate.getPokemonEntrenador().getVitalidad() / 100);
		// Barra de vida Rival
		this.pbRival.setProgress((double) combate.getPokemonEntrenadorRival().getVitalidadActual() * 100
				/ (double) combate.getPokemonEntrenadorRival().getVitalidad() / 100);
	}

	/**
	 * Modifica el texto de los 4 botones de movimiento introduciendo el nombre de
	 * los movimientos del pokemon en combate.
	 */
	public void setTextoBotones() {
		btnMov1.setText(combate.getPokemonEntrenador().getMovimientos().get(0).getNombre());
		btnMov2.setText(combate.getPokemonEntrenador().getMovimientos().get(1).getNombre());
		btnMov3.setText(combate.getPokemonEntrenador().getMovimientos().get(2).getNombre());
		btnMov4.setText(combate.getPokemonEntrenador().getMovimientos().get(3).getNombre());
	}

	/**
	 * Modifica el texto del lblNombrePokemon y lblNombrePokemonRival con los nombre
	 * de los Pokemon en combate.
	 */
	public void setTextoLabels() {
		lblNombrePokemon.setText(combate.getPokemonEntrenador().getNombre());
		lblNombrePokemonRival.setText(combate.getPokemonEntrenadorRival().getNombre());
	}

	/**
	 * Selecciona el movimiento numero 1 del Pokemon en combate del Entrenador y
	 * ejecuta el turno completo. Si no posee un movimiento en esta posicion se sale
	 * del metodo
	 * 
	 * @param event
	 */
	@FXML
	public void realizarMov1(ActionEvent event) {
		combate.elegirMovimientoEntrenador(0);
		if (combate.getMovimientoEntrenador().getTipoAtaque() == null) {
			return;
		}
		combate.elegirMovimientoEntrenadorRival();

		if (combate.comprobarEstamina()) {
			combate.getTurno().contadorTurno();
			ejecutarCombate();
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("No tienes estamina para realizar este movimiento");
			alert.showAndWait();
		}

		if (combate.finalTurno()) {

			calculoFinal();

			Stage stage = (Stage) this.btnMov1.getScene().getWindow();
			stage.close();
		}

	}

	/**
	 * Selecciona el movimiento numero 2 del Pokemon en combate del Entrenador y
	 * ejecuta el turno completo. Si no posee un movimiento en esta posicion se sale
	 * del metodo
	 * 
	 * @param event
	 */
	@FXML
	public void realizarMov2(ActionEvent event) {
		combate.elegirMovimientoEntrenador(1);
		if (combate.getMovimientoEntrenador().getTipoAtaque() == null) {
			return;
		}
		combate.elegirMovimientoEntrenadorRival();

		if (combate.comprobarEstamina()) {
			combate.getTurno().contadorTurno();
			ejecutarCombate();
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("No tienes estamina para realizar este movimiento");
			alert.showAndWait();
		}

		if (combate.finalTurno()) {

			calculoFinal();

			Stage stage = (Stage) this.btnMov2.getScene().getWindow();
			stage.close();
		}
	}

	/**
	 * Selecciona el movimiento numero 3 del Pokemon en combate del Entrenador y
	 * ejecuta el turno completo. Si no posee un movimiento en esta posicion se sale
	 * del metodo
	 * 
	 * @param event
	 */
	@FXML
	public void realizarMov3(ActionEvent event) {
		combate.elegirMovimientoEntrenador(2);
		if (combate.getMovimientoEntrenador().getTipoAtaque() == null) {
			return;
		}
		combate.elegirMovimientoEntrenadorRival();

		if (combate.comprobarEstamina()) {
			combate.getTurno().contadorTurno();
			ejecutarCombate();
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("No tienes estamina para realizar este movimiento");
			alert.showAndWait();
		}

		if (combate.finalTurno()) {

			calculoFinal();

			Stage stage = (Stage) this.btnMov3.getScene().getWindow();
			stage.close();
		}
	}

	/**
	 * Selecciona el movimiento numero 2 del Pokemon en combate del Entrenador y
	 * ejecuta el turno completo. Si no posee un movimiento en esta posicion se sale
	 * del metodo
	 * 
	 * @param event
	 */
	@FXML
	public void realizarMov4(ActionEvent event) {
		combate.elegirMovimientoEntrenador(3);
		if (combate.getMovimientoEntrenador().getTipoAtaque() == null) {
			return;
		}
		combate.elegirMovimientoEntrenadorRival();

		if (combate.comprobarEstamina()) {
			combate.getTurno().contadorTurno();
			ejecutarCombate();
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("No tienes estamina para realizar este movimiento");
			alert.showAndWait();
		}

		if (combate.finalTurno()) {

			calculoFinal();

			Stage stage = (Stage) this.btnMov4.getScene().getWindow();
			stage.close();
		}
	}

	// Event Listener on Button[#btnDescansar].onAction
	/**
	 * Usa el metodo descansar() de Pokemon en el Pokemon del entrenador. Se ejecuta
	 * el turno del rival.
	 * 
	 * @param event
	 */
	@FXML
	public void descansar(ActionEvent event) {
		combate.getTurno().contadorTurno();
		combate.getPokemonEntrenador().descansar();
		// Mensaje descanso
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Informacion");
		alert.setContentText(combate.getPokemonEntrenador().getNombre() + " ha recuperado estamina");
		alert.showAndWait();

		try {
			Estado.estadoPostAccion(combate.getPokemonEntrenador());
			// Mensaje de Ataque y Estado
			alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText(combate.getPokemonEntrenador().getNombre() + " perdió vida por: "
					+ combate.getPokemonEntrenador().getEstado());
			alert.showAndWait();
		} catch (NullPointerException e) {

		}

		combate.elegirMovimientoEntrenadorRival();
		turnoRival();

		actualizarProgressBar();

		if (combate.finalTurno()) {

			calculoFinal();

			Stage stage = (Stage) this.btnDescansar.getScene().getWindow();
			stage.close();
		}

	}

	// Event Listener on Button[#btnCambiarPokemon].onAction
	/**
	 * Usa el metodo abrirEquipo(). Si se cambia de pokemon, se ejecuta el turno del
	 * rival. En caso de no cambiar, no transcurre el turno.
	 * 
	 * @param event
	 */
	@FXML
	public void cambiarPokemon(ActionEvent event) {

		Pokemon pokemon = combate.getPokemonEntrenador();

		abrirEquipo();
		setTextoBotones();
		setTextoLabels();

		try {
			File file = new File(combate.getPokemonEntrenador().getImgTrasera());
			Image image = new Image(file.toURI().toString());
			imgPokemonPropio.setImage(image);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (!combate.getPokemonEntrenador().equals(pokemon)) {
			combate.getTurno().contadorTurno();
			combate.elegirMovimientoEntrenadorRival();
			turnoRival();
			actualizarProgressBar();
			if (combate.finalTurno()) {

				calculoFinal();

				Stage stage = (Stage) this.btnCambiarPokemon.getScene().getWindow();
				stage.close();
			}
		}

	}

	// Event Listener on Button[#btnHuir].onAction
	/**
	 * Concede la victoria al rival, y se resta el valor correspondiente al atributo
	 * pokedollar del Entrenador perder el combate. Cierra la ventana.
	 * 
	 * @param event
	 */
	@FXML
	public void huir(ActionEvent event) {
		combate.abandonarCombate();

		if (combate.comprobarGanador()) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText(combate.getEntrenador().getNombre() + " obtuvo: " + combate.calcularGananciaPerdida()
					+ " pokedollares");
			alert.showAndWait();

		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText(combate.getEntrenador().getNombre() + " perdió: " + combate.calcularGananciaPerdida()
					+ " pokedollares");
			alert.showAndWait();
		}

		combate.entregarPokedollars(this.e);
		EntrenadorCRUD.actualizarPokedollar(this.e);
		mediaPlayer.stop();

		Stage stage = (Stage) this.btnHuir.getScene().getWindow();
		stage.close();
	}

	/**
	 * Abre una nueva ventana de la vista CambioPokemon.
	 */
	public void abrirEquipo() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/CambioPokemon.fxml"));

			// Cargo la ventana
			Parent root = loader.load();

			CambioPokemonController controller = loader.getController();
			controller.initAttributes(combate);

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Comprueba que Pokemon ataca antes y ejecuta los turnos en relacion a ello.
	 */
	public void ejecutarCombate() {

		if (combate.comprobarVelocidad()) {
			turnoJugador();
			if (combate.getPokemonEntrenadorRival().getVitalidadActual() > 0) {
				turnoRival();
			}
			actualizarProgressBar();
		} else {
			turnoRival();
			if (combate.getPokemonEntrenador().getVitalidadActual() > 0) {
				turnoJugador();
			}
			actualizarProgressBar();
		}

	}

	/**
	 * Ejecuta el turno del jugador.
	 */
	public void turnoJugador() {

		try {
			// TURNO JUGADOR------------------------------------------------------
			if (Estado.estadoPreAccion(combate.getPokemonEntrenador())) {

				// Mensaje de Estado
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Informacion");
				alert.setContentText(combate.getPokemonEntrenador().getNombre() + " no ha podido atacar por: "
						+ combate.getPokemonEntrenador().getEstado());
				alert.showAndWait();

			} else {
				try {
					combate.ataquePropio();
					// Mensaje de Ataque
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Informacion");
					alert.setContentText(combate.getPokemonEntrenador().getNombre() + " usó: "
							+ combate.getMovimientoEntrenador().getNombre());
					alert.showAndWait();
				} catch (NullPointerException ex) {

				}

				if (Estado.estadoPostAccion(combate.getPokemonEntrenador())) {
					// Mensaje de Ataque y Estado
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Informacion");
					alert.setContentText(combate.getPokemonEntrenador().getNombre() + " perdió vida por: "
							+ combate.getPokemonEntrenador().getEstado());
					alert.showAndWait();
				}

				if (combate.pokemonPropioKO()) {

					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Informacion");
					alert.setContentText(combate.getPokemonEntrenador().getNombre() + " se ha debilitado");
					alert.showAndWait();

					combate.setMovimientoEntrenador(null);

					if (combate.comprobarKO()) {
						return;
					}

					abrirEquipo();
					setTextoBotones();
					setTextoLabels();

					try {
						File file = new File(combate.getPokemonEntrenador().getImgTrasera());
						Image image = new Image(file.toURI().toString());
						imgPokemonPropio.setImage(image);
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}

				if (combate.pokemonRivalKO()) {

					// mensaje muerte rival
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Informacion");
					alert.setContentText(combate.getPokemonEntrenadorRival().getNombre() + " rival se ha debilitado");
					alert.showAndWait();

					combate.obtenerExperiencia(this.e);
					combate.setMovimientoEntrenadorRival(null);

					if (combate.comprobarKO()) {
						return;
					} else {

						combate.pokemonRivalCambio();
						setTextoLabels();

						alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setHeaderText(null);
						alert.setTitle("Informacion");
						alert.setContentText("El rival sacó a: " + combate.getPokemonEntrenadorRival().getNombre());
						alert.showAndWait();

						try {
							File file = new File(combate.getPokemonEntrenadorRival().getImgFrontal());
							Image image = new Image(file.toURI().toString());
							imgPokemonRival.setImage(image);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}

				}

			}
		} catch (NullPointerException e) {

			try {
				combate.ataquePropio();
				// Mensaje de Ataque
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Informacion");
				alert.setContentText(combate.getPokemonEntrenador().getNombre() + " usó: "
						+ combate.getMovimientoEntrenador().getNombre());
				alert.showAndWait();
			} catch (NullPointerException ex) {

			}

			if (combate.pokemonPropioKO()) {
				// Mensaje muerte Pokemon
				// abrir ventana seleccion pokemon
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Informacion");
				alert.setContentText(combate.getPokemonEntrenador().getNombre() + " se ha debilitado");
				alert.showAndWait();

				combate.setMovimientoEntrenador(null);

				if (combate.comprobarKO()) {
					return;
				}

				abrirEquipo();
				setTextoBotones();
				setTextoLabels();
				try {
					File file = new File(combate.getPokemonEntrenador().getImgTrasera());
					Image image = new Image(file.toURI().toString());
					imgPokemonPropio.setImage(image);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}

			if (combate.pokemonRivalKO()) {

				// mensaje muerte rival
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Informacion");
				alert.setContentText(combate.getPokemonEntrenadorRival().getNombre() + " rival se ha debilitado");
				alert.showAndWait();

				combate.obtenerExperiencia(this.e);
				combate.setMovimientoEntrenadorRival(null);

				if (combate.comprobarKO()) {
					return;
				} else {
					combate.pokemonRivalCambio();
					setTextoLabels();

					alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Informacion");
					alert.setContentText("El rival sacó a: " + combate.getPokemonEntrenadorRival().getNombre());
					alert.showAndWait();

					try {
						File file = new File(combate.getPokemonEntrenadorRival().getImgFrontal());
						Image image = new Image(file.toURI().toString());
						imgPokemonRival.setImage(image);
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}

			}

		}

	}

	/**
	 * Ejecuta el turno del rival.
	 */
	public void turnoRival() {
		
		try {
			// TURNO RIVAL------------------------------------------------------
			if (Estado.estadoPreAccion(combate.getPokemonEntrenadorRival())) {

				// Mensaje de estado
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Informacion");
				alert.setContentText(combate.getPokemonEntrenadorRival().getNombre() + " no ha podido atacar por: "
						+ combate.getPokemonEntrenadorRival().getEstado());
				alert.showAndWait();

			} else if (!combate.comprobarEstaminaRival()) {
				combate.getPokemonEntrenadorRival().descansar();
				// Mensaje de Descanso
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Informacion");
				alert.setContentText(combate.getPokemonEntrenadorRival().getNombre() + " rival recuperó estamina");
				alert.showAndWait();

			} else {
				try {
					combate.ataqueRival();
					// Mensaje ataque
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Informacion");
					alert.setContentText(combate.getPokemonEntrenadorRival().getNombre() + " rival usó: "
							+ combate.getMovimientoEntrenadorRival().getNombre());
					alert.showAndWait();
				} catch (NullPointerException ex) {

				}
				if (Estado.estadoPostAccion(combate.getPokemonEntrenadorRival())) {
					// Mensaje Estado Rival
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Informacion");
					alert.setContentText(combate.getPokemonEntrenadorRival().getNombre() + " perdió vida por: "
							+ combate.getPokemonEntrenadorRival().getEstado());
					alert.showAndWait();

				}

				if (combate.pokemonPropioKO()) {
					// Mensaje muerte pokemon
					// abrir ventana seleccion pokemon
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Informacion");
					alert.setContentText(combate.getPokemonEntrenador().getNombre() + " se ha debilitado");
					alert.showAndWait();

					combate.setMovimientoEntrenador(null);

					if (combate.comprobarKO()) {
						return;
					}

					abrirEquipo();
					setTextoBotones();
					setTextoLabels();

					try {
						File file = new File(combate.getPokemonEntrenador().getImgTrasera());
						Image image = new Image(file.toURI().toString());
						imgPokemonPropio.setImage(image);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

				if (combate.pokemonRivalKO()) {

					// mensaje muerte rival
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Informacion");
					alert.setContentText(combate.getPokemonEntrenadorRival().getNombre() + " rival se ha debilitado");
					alert.showAndWait();

					combate.obtenerExperiencia(this.e);
					combate.setMovimientoEntrenadorRival(null);

					if (combate.comprobarKO()) {
						return;
					} else {
						combate.pokemonRivalCambio();
						setTextoLabels();

						alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setHeaderText(null);
						alert.setTitle("Informacion");
						alert.setContentText("El rival sacó a: " + combate.getPokemonEntrenadorRival().getNombre());
						alert.showAndWait();

						try {
							File file = new File(combate.getPokemonEntrenadorRival().getImgFrontal());
							Image image = new Image(file.toURI().toString());
							imgPokemonRival.setImage(image);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}

				}

			}
		} catch (NullPointerException e) {

			if (!combate.comprobarEstaminaRival()) {
				combate.getPokemonEntrenadorRival().descansar();
				// Mensaje de Descanso
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Informacion");
				alert.setContentText(combate.getPokemonEntrenadorRival().getNombre() + " rival recuperó estamina");
				alert.showAndWait();
			} else {
				try {
					combate.ataqueRival();
					// Mensaje de Ataque rival
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Informacion");
					alert.setContentText(combate.getPokemonEntrenadorRival().getNombre() + " rival usó: "
							+ combate.getMovimientoEntrenadorRival().getNombre());
					alert.showAndWait();
				} catch (NullPointerException ex) {

				}

			}

			if (combate.pokemonPropioKO()) {
				// Mensaje muerte pokemon
				// abrir ventana seleccion pokemon
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Informacion");
				alert.setContentText(combate.getPokemonEntrenador().getNombre() + " se ha debilitado");
				alert.showAndWait();

				combate.setMovimientoEntrenador(null);

				if (combate.comprobarKO()) {
					return;
				}

				abrirEquipo();
				setTextoBotones();
				setTextoLabels();

				try {
					File file = new File(combate.getPokemonEntrenador().getImgTrasera());
					Image image = new Image(file.toURI().toString());
					imgPokemonPropio.setImage(image);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			if (combate.pokemonRivalKO()) {

				// mensaje muerte rival
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Informacion");
				alert.setContentText(combate.getPokemonEntrenadorRival().getNombre() + " rival se ha debilitado");
				alert.showAndWait();

				combate.obtenerExperiencia(this.e);
				combate.setMovimientoEntrenadorRival(null);

				if (combate.comprobarKO()) {
					return;
				} else {
					combate.pokemonRivalCambio();
					setTextoLabels();

					alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Informacion");
					alert.setContentText("El rival sacó a: " + combate.getPokemonEntrenadorRival().getNombre());
					alert.showAndWait();

					try {
						File file = new File(combate.getPokemonEntrenadorRival().getImgFrontal());
						Image image = new Image(file.toURI().toString());
						imgPokemonRival.setImage(image);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}

		}

	}

	/**
	 * Recorre el equipo del Entrenador. Si alguno de los pokemon tiene experiencia
	 * suficiente para subir de nivel, se ejecuta el metodo subirNivel() sobre el,
	 * se comprueba si puede aprender un movimiento, en caso afirmativo, abre la
	 * ventana de la vista NuevoMovimiento, y comprueba si su nivel es igual al
	 * nivel de evolucion, si es asi, evoluciona. Finalmente se ejecuta el metodo
	 * entregarPokedollars().
	 */
	public void calculoFinal() {
		for (int i = 0; i < combate.getEntrenador().getEquipo().size(); i++) {
			if (e.getEquipo().get(i).subirNivel()) {
				// Mensaje LVL up
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Informacion");
				alert.setContentText(
						e.getEquipo().get(i).getNombre() + " subió al nivel: " + e.getEquipo().get(i).getNivel());
				alert.showAndWait();
				
				Combate.logs((e.getEquipo().get(i).getNombre() + " subió al nivel: " + e.getEquipo().get(i).getNivel()));
				CombateCRUD.updateEstadisticas(e.getEquipo().get(i));

				if (e.getEquipo().get(i).aprenderMovimiento()) {
					// Nueva vista aprender movimiento
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/NuevoMovimiento.fxml"));

						// Cargo la ventana
						Parent root = loader.load();

						NuevoMovimientoController controller = loader.getController();
						controller.initAttributes(e.getEquipo().get(i), e);

						Scene scene = new Scene(root);
						Stage stage = new Stage();
						stage.initModality(Modality.APPLICATION_MODAL);
						stage.setScene(scene);
						stage.setResizable(false);
						stage.showAndWait();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (e.getEquipo().get(i).evolucionar()) {

					e.getEquipo().set(i, PokemonCRUD.selectUnPokemon(e.getEquipo().get(i)));
					// Mensaje evolucion
					alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Informacion");
					alert.setContentText(combate.getEntrenador().getEquipo().get(i).getNombre() + " evolucionó a: "
							+ e.getEquipo().get(i).getNombre());
					alert.showAndWait();
					
					Combate.logs((combate.getEntrenador().getEquipo().get(i).getNombre() + " evolucionó a: "
							+ e.getEquipo().get(i).getNombre()));

				}
			}
		}

		if (combate.comprobarGanador()) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText(combate.getEntrenador().getNombre() + " obtuvo: " + combate.calcularGananciaPerdida()
					+ " pokedollares");
			alert.showAndWait();

		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText(combate.getEntrenador().getNombre() + " perdió: " + combate.calcularGananciaPerdida()
					+ " pokedollares");
			alert.showAndWait();
		}

		combate.entregarPokedollars(this.e);
		EntrenadorCRUD.actualizarPokedollar(this.e);
		mediaPlayer.stop();
	}
}
