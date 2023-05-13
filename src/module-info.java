module PokemonGraphicTest {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.media;
	requires junit;
	requires org.junit.jupiter.api;
	
	opens application to javafx.graphics, javafx.fxml, javafx.controls, javafx.base;
	opens modelo to javafx.graphics, javafx.fxml, javafx.controls, javafx.base;
}
