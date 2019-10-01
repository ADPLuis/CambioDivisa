package dad.javafx.CambioDivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisaApp extends Application {
	
	private TextField origen, destino;
	private ComboBox<String> comboOrig, comboDest; 
	Button cambio;
	Divisa euro = new Divisa("Euro", 1.0);
	Divisa libra = new Divisa("Libra", 0.8873);
	Divisa dolar = new Divisa("Dolar", 1.2007);
	Divisa yen = new Divisa("Yen", 133.59);
	Divisa divOrigen = euro;
	Divisa divDestino = yen;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		origen = new TextField();
		origen.setPromptText("0");
		
		destino = new TextField();
		destino.setPromptText("0");
		destino.setEditable(false);
		
		
		comboOrig = new ComboBox<String>();
		comboOrig.getItems().addAll("Euro","Libra","Dolar","Yen");
		comboOrig.setPromptText("Selecciona divisa de origen");
		
		
		comboDest = new ComboBox<String>();
		comboDest.getItems().addAll("Yen","Libra","Dolar","Euro");
		comboDest.setPromptText("Selecciona divisa de destino");
		
		cambio = new Button("Cambiar");
		cambio.setDefaultButton(true);
		cambio.setAlignment(Pos.CENTER);
		cambio.setOnAction(e -> onLoginButtonAction(e));
		
		HBox primera = new HBox(5, origen, comboOrig);
		HBox segunda = new HBox(5, destino, comboDest);
		HBox tercera = new HBox(cambio);
		tercera.setAlignment(Pos.CENTER);
		
		VBox root = new VBox(5, primera,segunda,tercera);
		root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root, 320,200);
		
		primaryStage.setTitle("Calculadora de cambio de divisas");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void onLoginButtonAction(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Double cantidadOrig=(double) 0;
		try {
			cantidadOrig = Double.parseDouble(origen.getText());
		} catch (Exception excp){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("Error");
			alert.setContentText("No introduzcas valores que no sean numéricos");
			alert.showAndWait();
		}
		
		String divisaOrigen = comboOrig.getSelectionModel().getSelectedItem();
		String divisaDestino = comboDest.getSelectionModel().getSelectedItem();
		String resultado;
		
		if (divisaOrigen == yen.getNombre()) {
			divOrigen=yen;
		} else if (divisaOrigen == libra.getNombre()) {
			divOrigen=libra;
		} else if (divisaOrigen == dolar.getNombre()) {
			divOrigen=dolar;
		} else {
			divOrigen = euro;
		}
		
		if (divisaDestino == yen.getNombre()) {
			divDestino=yen;
		} else if (divisaDestino == libra.getNombre()) {
			divDestino=libra;
		} else if (divisaDestino == dolar.getNombre()) {
			divDestino=dolar;
		} else {
			divDestino = euro;
		}
		
			resultado=""+Divisa.fromTo(divOrigen, divDestino, cantidadOrig);
			destino.setText(resultado);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
