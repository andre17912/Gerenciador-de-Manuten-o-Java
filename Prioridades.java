import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class Prioridades {
    
    private Stage stage;
    
    public void mostrar(Stage ownerStage) {
        stage = new Stage();
        stage.setTitle("Prioridades");
        stage.initOwner(ownerStage);
        
        BorderPane root = new BorderPane();

        // Sidebar
        VBox sidebar = criarSidebar(stage);
        root.setLeft(sidebar);

        // Conteúdo central
        VBox centerContent = new VBox(20);
        centerContent.setId("main-content");
        centerContent.setAlignment(Pos.CENTER);
        centerContent.setPadding(new Insets(30));
        
        Label titulo = new Label("Gerenciar Prioridades");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        
        // Aqui você adiciona o conteúdo específico da tela de prioridades
        Label emBreve = new Label("Conteúdo em desenvolvimento...");
        emBreve.setStyle("-fx-font-size: 16px; -fx-text-fill: #666;");
        
        centerContent.getChildren().addAll(titulo, emBreve);
        
        root.setCenter(centerContent);

        Scene scene = new Scene(root, 860, 640);
        
        try {
            scene.getStylesheets().add(
                getClass().getResource("style.css").toExternalForm()
            );
        } catch (Exception e) {
            System.out.println("CSS não encontrado: " + e.getMessage());
        }
        
        stage.setScene(scene);
        stage.show();
    }
    
    private VBox criarSidebar(Stage stage) {
        VBox sidebar = new VBox(10);
        sidebar.setPrefWidth(200);
        sidebar.setStyle("-fx-background-color: #7A2021; -fx-padding: 10px;");
        sidebar.setAlignment(Pos.TOP_CENTER);
        
        // Logo (opcional)
        try {
            Image logo = new Image(getClass().getResource("/Imagens/logo.jpg").toExternalForm());
            ImageView logoView = new ImageView(logo);
            logoView.setFitWidth(120);
            logoView.setPreserveRatio(true);
            sidebar.getChildren().add(logoView);
        } catch (Exception e) {
            System.out.println("Logo não encontrada");
        }
        
        Button voltarBtn = new Button("← Voltar");
        voltarBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: white; -fx-padding: 10px;");
        voltarBtn.setOnAction(e -> stage.close());
        
        sidebar.getChildren().add(voltarBtn);
		
		
		
		
        
        return sidebar;
    }
}
