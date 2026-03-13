import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement; 
import java.sql.ResultSet;          
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import java.sql.Statement;  // Add this line


public class gerenciador extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Gerenciador");

        BorderPane root = new BorderPane();

        // USE reusable sidebar
        VBox sidebar = createSidebar(primaryStage);

        VBox centerContent = new VBox();
        centerContent.setId("main-content");
        centerContent.setSpacing(10);
        centerContent.setAlignment(Pos.CENTER);

        root.setLeft(sidebar);
        root.setCenter(centerContent);

        Scene scene = new Scene(root, 860, 640);
        scene.getStylesheets().add(
                getClass().getResource("style.css").toExternalForm()
        );
        
           String url = "jdbc:sqlite:dados.db";

        // Center buttons
        Button Status = new Button("Status das Maquinas");
        Button Prioridades = new Button("Prioridades");
        Button Servicos = new Button("Serviços externos");
        Button Gerente = new Button("Acesso de Gerente");

        Status.getStyleClass().add("Status-button");
        Prioridades.getStyleClass().add("Status-button");
        Servicos.getStyleClass().add("Status-button");
        Gerente.getStyleClass().add("Status-button");

        centerContent.getChildren().addAll(Status, Prioridades, Servicos, Gerente);

Prioridades.setOnAction(e -> {

    Prioridades telaPrioridades = new Prioridades();
    telaPrioridades.mostrar(primaryStage);

    
});
        
        // Ação do botão Gerente
        Gerente.setOnAction(es -> {
            // Criar uma nova janela para o login
            Stage loginStage = new Stage();
            loginStage.setTitle("Login do Gerente");
            
            // Configurar para não mostrar os botões da primaryStage
            loginStage.initOwner(primaryStage);
            loginStage.initModality(javafx.stage.Modality.WINDOW_MODAL); // Bloqueia a janela principal
            
            // Layout principal
            VBox loginLayout = new VBox(20);
            loginLayout.setAlignment(Pos.CENTER);
            loginLayout.setPadding(new Insets(40));
            loginLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #f8f9fa, #e9ecef);");
            
            // Título
            Label titleLabel = new Label("Área do Gerente");
            titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #7A2021;");
            
            // Subtítulo
            Label subtitleLabel = new Label("Por favor, faça o login para continuar");
            subtitleLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #6c757d;");
            
            // Container do formulário
            GridPane formGrid = new GridPane();
            formGrid.setHgap(15);
            formGrid.setVgap(15);
            formGrid.setAlignment(Pos.CENTER);
            formGrid.setPadding(new Insets(20, 0, 20, 0));
            
            // Campo de usuário
            Label userIconLabel = new Label("👤");
            userIconLabel.setStyle("-fx-font-size: 18px;");
            Label userLabel = new Label("Usuário:");
            userLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            TextField userField = new TextField();
            userField.setPromptText("Digite seu usuário");
            userField.setPrefWidth(250);
            userField.setStyle("-fx-padding: 10px; -fx-background-radius: 5px;");
            
            // Campo de senha
            Label passIconLabel = new Label("🔒");
            passIconLabel.setStyle("-fx-font-size: 18px;");
            Label passLabel = new Label("Senha:");
            passLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            PasswordField passField = new PasswordField();
            passField.setPromptText("Digite sua senha");
            passField.setPrefWidth(250);
            passField.setStyle("-fx-padding: 10px; -fx-background-radius: 5px;");
            
            // Adicionar ao grid
            formGrid.add(userIconLabel, 0, 0);
            formGrid.add(userLabel, 1, 0);
            formGrid.add(userField, 2, 0);
            formGrid.add(passIconLabel, 0, 1);
            formGrid.add(passLabel, 1, 1);
            formGrid.add(passField, 2, 1);
            
            // Container dos botões
            HBox buttonBox = new HBox(20);
            buttonBox.setAlignment(Pos.CENTER);
            buttonBox.setPadding(new Insets(20, 0, 10, 0));
            
            // Botão Entrar
            Button entrarBtn = new Button("Entrar");
            entrarBtn.setPrefWidth(120);
            entrarBtn.setPrefHeight(40);
            entrarBtn.setStyle(
                "-fx-background-color: #4CAF50;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 5px;" +
                "-fx-cursor: hand;"
            );
            
            // Efeito hover no botão Entrar
            entrarBtn.setOnMouseEntered(e -> 
                entrarBtn.setStyle(
                    "-fx-background-color: #45a049;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 14px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 5px;" +
                    "-fx-cursor: hand;"
                )
            );
            entrarBtn.setOnMouseExited(e -> 
                entrarBtn.setStyle(
                    "-fx-background-color: #4CAF50;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 14px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 5px;" +
                    "-fx-cursor: hand;"
                )
            );
            
            // Botão Sair
            Button sairBtn = new Button("Sair");
            sairBtn.setPrefWidth(120);
            sairBtn.setPrefHeight(40);
            sairBtn.setStyle(
                "-fx-background-color: #dc3545;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 5px;" +
                "-fx-cursor: hand;"
            );
            
            // Efeito hover no botão Sair
            sairBtn.setOnMouseEntered(e -> 
                sairBtn.setStyle(
                    "-fx-background-color: #c82333;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 14px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 5px;" +
                    "-fx-cursor: hand;"
                )
            );
            sairBtn.setOnMouseExited(e -> 
                sairBtn.setStyle(
                    "-fx-background-color: #dc3545;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 14px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 5px;" +
                    "-fx-cursor: hand;"
                )
            );
            
            buttonBox.getChildren().addAll(entrarBtn, sairBtn);
            
            // Label para mensagens
            Label messageLabel = new Label();
            messageLabel.setStyle("-fx-text-fill: #dc3545; -fx-font-size: 12px;");
            
            loginLayout.getChildren().addAll(titleLabel, subtitleLabel, formGrid, buttonBox, messageLabel);
            
            // Ação do botão Sair - FECHA APENAS A JANELA DE LOGIN
            sairBtn.setOnAction(e -> loginStage.close());
            
            // Ação do botão Entrar
            entrarBtn.setOnAction(e -> {
                String username = userField.getText();
                String password = passField.getText();
                
                if (username.isEmpty() || password.isEmpty()) {
                    messageLabel.setText("❌ Preencha todos os campos!");
                    messageLabel.setStyle("-fx-text-fill: #dc3545; -fx-font-size: 12px;");
                } else {
                  
                    if (username.equals("admin") && password.equals("1234")) {
                        messageLabel.setText("✅ Login bem-sucedido!");
                        messageLabel.setStyle("-fx-text-fill: #28a745; -fx-font-size: 12px;");
                        
                        // Fecha a janela de login após 1 segundo
                        new Thread(() -> {
                            try {
                                Thread.sleep(1000);
                                javafx.application.Platform.runLater(() -> {
                                    loginStage.close();
                                    // Aqui você pode abrir a janela do gerente
                                    abrirJanelaGerente(loginStage, username);
                                });
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }).start();
                    } else {
                        messageLabel.setText("❌ Usuário ou senha incorretos!");
                        messageLabel.setStyle("-fx-text-fill: #dc3545; -fx-font-size: 12px;");
                    }
                }
            });
            
            // Permite fechar com a tecla ESC
            loginLayout.setOnKeyPressed(event -> {
                if (event.getCode() == javafx.scene.input.KeyCode.ESCAPE) {
                    loginStage.close();
                }
            });
            
            Scene loginScene = new Scene(loginLayout, 500, 400);
            
            // Carregar CSS se existir
            try {
                loginScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            } catch (Exception ex) {
                // CSS não encontrado, continuar sem ele
            }
            
            loginStage.setScene(loginScene);
            loginStage.setResizable(false); // Impede redimensionamento
            loginStage.show();
        });

        // Ação do botão Status
        Status.setOnAction(event -> {
            Stage newStage = new Stage();
            newStage.setTitle("Status das Maquinas");

            BorderPane newRoot = new BorderPane();

            VBox sidebar2 = createSidebar(newStage);

            VBox newCenter = new VBox();
            newCenter.setAlignment(Pos.TOP_LEFT);
            newCenter.setSpacing(10);
            newRoot.setLeft(sidebar2);
            newRoot.setCenter(newCenter);

            Scene newScene = new Scene(newRoot, 860, 640);
            newScene.getStylesheets().add(
                    getClass().getResource("style.css").toExternalForm()
            );

            // ------------------------------
            // Barra de seleção do Setor
            Label setorLabel = new Label("Setor:");
            setorLabel.getStyleClass().add("label1");

            ComboBox<String> setorCombo = new ComboBox<>();

            // Puxa dados do banco
            try (Connection conn = DriverManager.getConnection(url)) {
                var stmt = conn.createStatement();
                var rs = stmt.executeQuery("SELECT nome_setor FROM setores ORDER BY id");
                while (rs.next()) {
                    setorCombo.getItems().add(rs.getString("nome_setor"));
                }
                if (!setorCombo.getItems().isEmpty()) {
                    setorCombo.setValue(setorCombo.getItems().get(0));
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao carregar setores: " + ex.getMessage());
            }

            // Botão de voltar
            Button voltar = new Button("Voltar");
            voltar.getStyleClass().add("voltar1");
            Button ir = new Button("Prosseguir");
            ir.getStyleClass().add("voltar1");
            voltar.setOnAction(e -> newStage.close());

            HBox topBox = new HBox(10, setorLabel, setorCombo, voltar, ir);
            topBox.setAlignment(Pos.CENTER_LEFT);
            topBox.setPadding(new Insets(25));

            newCenter.getChildren().add(topBox);

            // ------------------------------
            // Barra de seleção de Máquinas
            Label maquinasLabel = new Label("Máquinas:");
            maquinasLabel.getStyleClass().add("label1");

            ComboBox<String> maquinasCombo = new ComboBox<>();
            maquinasCombo.setPrefWidth(300);
            maquinasCombo.setMinWidth(200);
            maquinasCombo.setMaxWidth(Double.MAX_VALUE);

            // FUNÇÃO PARA CARREGAR MÁQUINAS POR SETOR
            java.util.function.Consumer<String> carregarMaquinas = (setorSelecionado) -> {
                maquinasCombo.getItems().clear();
                
                if (setorSelecionado == null) return;
                
                String sql = "SELECT m.nome_maquina FROM maquinas m " +
                             "JOIN setores s ON m.setor_id = s.id " +
                             "WHERE s.nome_setor = ? ORDER BY m.nome_maquina";
                
                try (Connection conn = DriverManager.getConnection(url);
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    
                    pstmt.setString(1, setorSelecionado);
                    ResultSet rs = pstmt.executeQuery();
                    
                    while (rs.next()) {
                        maquinasCombo.getItems().add(rs.getString("nome_maquina"));
                    }
                    
                    if (!maquinasCombo.getItems().isEmpty()) {
                        maquinasCombo.setValue(maquinasCombo.getItems().get(0));
                    }
                    
                } catch (SQLException ex) {
                    System.out.println("Erro ao carregar máquinas do setor " + setorSelecionado + ": " + ex.getMessage());
                }
            };

            // CARREGA MÁQUINAS QUANDO O SETOR MUDA
            setorCombo.setOnAction(e -> {
                String setorSelecionado = setorCombo.getValue();
                carregarMaquinas.accept(setorSelecionado);
            });

            // CARREGA AS MÁQUINAS DO PRIMEIRO SETOR
            if (!setorCombo.getItems().isEmpty()) {
                carregarMaquinas.accept(setorCombo.getValue());
            }

            HBox maquinasBox = new HBox(10, maquinasLabel, maquinasCombo);
            maquinasBox.setAlignment(Pos.CENTER_LEFT);
            maquinasBox.setPadding(new Insets(10, 25, 10, 25));

            newCenter.getChildren().add(maquinasBox);

            // ------------------------------
            newStage.setScene(newScene);
            newStage.show();

            ir.setOnAction(e -> {
                String maquinaSelecionada = maquinasCombo.getValue();
                if (maquinaSelecionada != null && !maquinaSelecionada.isEmpty()) {
                    newStage.close();    
                    Stage statusMaquina = new Stage();
                    statusMaquina.setTitle("Status - " + maquinaSelecionada);
                    BorderPane maquina = new BorderPane();

                    Scene sceneNovo = new Scene(maquina, 860, 640);
                    sceneNovo.getStylesheets().add(
                            getClass().getResource("style.css").toExternalForm());

                    // Mostra informações da máquina específica
                    VBox content = new VBox(20);
                    content.setAlignment(Pos.CENTER);
                    content.setPadding(new Insets(20));
                    
                    Label titleLabel = new Label("Máquina: " + maquinaSelecionada);
                    titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
                    
                    Label infoLabel = new Label("Status de funcionamento :  ");
                    
                    Button voltarBtn = new Button("Voltar");
                    voltarBtn.setOnAction(ev -> statusMaquina.close());
                    
                    content.getChildren().addAll(titleLabel, infoLabel, voltarBtn);
                    maquina.setCenter(content);

                    statusMaquina.setScene(sceneNovo);
                    statusMaquina.show();
                    
                    // Carregar imagem
                    try {
                        Image work = new Image(getClass().getResource("/Imagens/Work.jpg").toExternalForm());
                    } catch (Exception ex) {
                        System.out.println("Imagem não encontrada: " + ex.getMessage());
                    }
                }
            });
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para abrir a janela do gerente após login
    private void abrirJanelaGerente(Stage ownerStage, String username) {
        Stage gerenteStage = new Stage();
        gerenteStage.setTitle("Painel do Gerente - " + username);
        
        // Layout da janela do gerente
        BorderPane root = new BorderPane();
        
        // Sidebar
        VBox sidebar = new VBox(20);
        sidebar.setPrefWidth(200);
        sidebar.setStyle("-fx-background-color: #7A2021; -fx-padding: 20px;");
        
        Label welcomeLabel = new Label("Bem-vindo,\n" + username);
        welcomeLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        welcomeLabel.setWrapText(true);
        
        Button logoutBtn = new Button("Sair");
        logoutBtn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: white;" +
            "-fx-border-color: white;" +
            "-fx-border-radius: 5px;" +
            "-fx-padding: 10px 20px;"
        );
        logoutBtn.setOnAction(e -> gerenteStage.close());
        
        sidebar.getChildren().addAll(welcomeLabel, logoutBtn);
        
        // Conteúdo central
        VBox centerContent = new VBox(20);
        centerContent.setAlignment(Pos.TOP_LEFT);
        centerContent.setPadding(new Insets(40));
        
        Label contentLabel = new Label("Conteúdo da Área do Gerente");
		Button Definir = new Button("Definir Prioridade");
		Button servicos = new Button ("Verificar atividades realizadas");
		Button Ordem = new Button ("Ordem de serviço");
        contentLabel.setStyle("-fx-font-size: 18px;");
        
        centerContent.getChildren().addAll(contentLabel,Definir,Ordem,servicos);
		
        
        root.setLeft(sidebar);
        root.setCenter(centerContent);
        
        Scene scene = new Scene(root, 860, 640);
		
		Definir.setOnAction(e -> {
    
    Stage Nova = new Stage();
    Nova.setTitle("Definição de Prioridade");
    BorderPane nova = new BorderPane();
    
    VBox sidebar2 = createSidebar(Nova);
    
    VBox def = new VBox(15); 
    def.setAlignment(Pos.TOP_LEFT);
    def.setSpacing(15);
    def.setPadding(new Insets(30));  
    
    nova.setLeft(sidebar2);
    nova.setCenter(def);
    
    Scene nov = new Scene(nova, 860, 640);
    
    try {
        nov.getStylesheets().add(
            getClass().getResource("style.css").toExternalForm()
        );
    } catch (Exception ex) {
        System.out.println("CSS não encontrado: " + ex.getMessage());
    }
    
    // ===== TÍTULO =====
    Label titulo = new Label("Definir Prioridade");
    titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #7A2021;");
    
    // ===== COMBOBOX DE SETORES (COM DADOS DO BANCO) =====
    Label setorLabel = new Label("Selecione o setor:");
    setorLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
    
    ComboBox<String> setorCombo = new ComboBox<>();
    setorCombo.setPrefWidth(300);
    setorCombo.setPromptText("Escolha um setor");
    
    // URL do banco de dados
    String url = "jdbc:sqlite:C:/Users/andre/Desktop/ProjetoJava/dados.db";
    
    // Puxa dados do banco para o ComboBox de setores
    try (Connection conn = DriverManager.getConnection(url)) {
        var stmt = conn.createStatement();
        var rs = stmt.executeQuery("SELECT nome_setor FROM setores ORDER BY id");
        
        while (rs.next()) {
            setorCombo.getItems().add(rs.getString("nome_setor"));
        }
        
        if (!setorCombo.getItems().isEmpty()) {
            setorCombo.setValue(setorCombo.getItems().get(0));
        }
    } catch (SQLException ex) {
        System.out.println("Erro ao carregar setores: " + ex.getMessage());
        // Dados de fallback caso o banco não funcione
        setorCombo.getItems().addAll("Produção", "Manutenção", "Administração");
        setorCombo.setValue("Produção");
    }
    
    // ===== COMBOBOX DE MÁQUINAS (será preenchido baseado no setor) =====
    Label maquinaLabel = new Label("Selecione a máquina:");
    maquinaLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
    
    ComboBox<String> maquinasCombo = new ComboBox<>();  // Só UMA declaração!
    maquinasCombo.setPrefWidth(300);
    maquinasCombo.setPromptText("Escolha uma máquina");
    
    // ===== COMBOBOX DE PRIORIDADE =====
    Label prioridadeLabel = new Label("Defina a prioridade:");
    prioridadeLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
    
    ComboBox<String> prioridadeCombo = new ComboBox<>();
    prioridadeCombo.getItems().addAll("Alta", "Média", "Baixa");
    prioridadeCombo.setValue("Média");
    prioridadeCombo.setPrefWidth(150);
    
    // ===== FUNÇÃO PARA CARREGAR MÁQUINAS POR SETOR =====
    java.util.function.Consumer<String> carregarMaquinas = (setorSelecionado) -> {
        maquinasCombo.getItems().clear();
        
        if (setorSelecionado == null || setorSelecionado.isEmpty()) {
            maquinasCombo.setPromptText("Selecione um setor primeiro");
            return;
        }
        
        String sql = "SELECT m.nome_maquina FROM maquinas m " +
                     "JOIN setores s ON m.setor_id = s.id " +
                     "WHERE s.nome_setor = ? ORDER BY m.nome_maquina";
        
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, setorSelecionado);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                maquinasCombo.getItems().add(rs.getString("nome_maquina"));
            }
            
            if (!maquinasCombo.getItems().isEmpty()) {
                maquinasCombo.setValue(maquinasCombo.getItems().get(0));
                maquinasCombo.setPromptText("Escolha uma máquina");
            } else {
                maquinasCombo.setPromptText("Nenhuma máquina neste setor");
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro ao carregar máquinas do setor " + setorSelecionado + ": " + ex.getMessage());
            // Dados de exemplo para teste
            maquinasCombo.getItems().addAll("Máquina A", "Máquina B", "Máquina C");
        }
    };
    
    // ===== EVENTO QUANDO SETOR MUDA =====
setorCombo.setOnAction(ev -> {
    String setorSelecionado = setorCombo.getValue();
    carregarMaquinas.accept(setorSelecionado);
});

// ===== CARREGA MÁQUINAS DO PRIMEIRO SETOR (se houver) =====
if (!setorCombo.getItems().isEmpty()) {
    carregarMaquinas.accept(setorCombo.getValue());
}

// ===== CAMPOS DE ENTRADA =====
Label prioridadesLabel = new Label("Prioridade:");
prioridadesLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

 ComboBox<String> prioridadesCombo = new ComboBox<>();
 prioridadesCombo.getItems().addAll("Alta", "Média", "Baixa", "Crítica");
 prioridadesCombo.setPromptText("Selecione a prioridade");
 prioridadesCombo.setPrefWidth(200);
 prioridadesCombo.setStyle(
    "-fx-font-size: 14px;" +
    "-fx-background-radius: 5px;" +
    "-fx-border-radius: 5px;" +
    "-fx-border-color: #ced4da;" +
    "-fx-border-width: 1px;"
  );

// ===== CAMPO DE OBSERVAÇÃO =====
Label observacaoLabel = new Label("Observação:");
observacaoLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

TextField observacaoField = new TextField();
observacaoField.setPromptText("Observações adicionais (opcional)");
observacaoField.setPrefWidth(400);

// ===== BOTÕES =====
Button salvarBtn = new Button("Salvar Prioridade");
salvarBtn.setStyle(
    "-fx-background-color: #4CAF50;" +
    "-fx-text-fill: white;" +
    "-fx-font-size: 14px;" +
    "-fx-font-weight: bold;" +
    "-fx-padding: 10px 20px;" +
    "-fx-background-radius: 5px;"
);

Button voltarBtn = new Button("Voltar");
voltarBtn.setStyle(
    "-fx-background-color: #6c757d;" +
    "-fx-text-fill: white;" +
    "-fx-font-size: 14px;" +
    "-fx-padding: 10px 20px;" +
    "-fx-background-radius: 5px;"
);
voltarBtn.setOnAction(ev -> Nova.close());

// Label para mensagens
Label mensagemLabel = new Label();
mensagemLabel.setStyle("-fx-font-size: 12px;");

// ===== VERIFICAR/CRIAR COLUNAS NO BANCO DE DADOS (executar uma vez) =====
try (Connection conn = DriverManager.getConnection(url);
     Statement stmt = conn.createStatement()) {

    // Verificar se as colunas existem (SQLite não tem IF NOT EXISTS para colunas)
    try {
        stmt.executeUpdate("ALTER TABLE maquinas ADD COLUMN observacao TEXT");
        System.out.println("Coluna 'observacao' criada com sucesso!");
    } catch (SQLException ex) {
        // Coluna provavelmente já existe
        System.out.println("Coluna 'observacao' já existe ou erro: " + ex.getMessage());
    }

    try {
        stmt.executeUpdate("ALTER TABLE maquinas ADD COLUMN prioridade TEXT");
        System.out.println("Coluna 'prioridade' criada com sucesso!");
    } catch (SQLException ex) {
        // Coluna provavelmente já existe
        System.out.println("Coluna 'prioridade' já existe ou erro: " + ex.getMessage());
    }

} catch (SQLException es) {
    System.out.println("Erro ao conectar ao banco: " + es.getMessage());
}

// ===== AÇÃO DO BOTÃO SALVAR =====
salvarBtn.setOnAction(ev -> {
    String setor = setorCombo.getValue();
    String maquina = maquinasCombo.getValue();
    String prioridade = prioridadeCombo.getValue();
    String observacao = observacaoField.getText();
    
    if (setor == null || maquina == null || prioridade == null) {
        mensagemLabel.setText("❌ Preencha todos os campos obrigatórios!");
        mensagemLabel.setStyle("-fx-text-fill: #dc3545;");
    } else {
        // Salvar no banco de dados
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(
             "UPDATE maquinas SET prioridade = ?, observacao = ? WHERE nome_maquina = ?")) {

            pstmt.setString(1, prioridade);
            pstmt.setString(2, observacao);
            pstmt.setString(3, maquina);


            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                mensagemLabel.setText("✅ Prioridade '" + prioridade + "' definida para " + maquina + "!");
                mensagemLabel.setStyle("-fx-text-fill: #28a745;");
                
                // Opcional: Limpar campos ou fechar após 2 segundos
                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                        javafx.application.Platform.runLater(() -> {
                            Nova.close();
                        });
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }).start();
            } else {
                mensagemLabel.setText("❌ Máquina não encontrada no banco de dados!");
                mensagemLabel.setStyle("-fx-text-fill: #dc3545;");
            }

        } catch (SQLException er) {
            mensagemLabel.setText("❌ Erro ao salvar: " + er.getMessage());
            mensagemLabel.setStyle("-fx-text-fill: #dc3545;");
            er.printStackTrace();
        }
    }
});
    HBox botoesBox = new HBox(15, salvarBtn, voltarBtn);
    botoesBox.setAlignment(Pos.CENTER_LEFT);
    botoesBox.setPadding(new Insets(20, 0, 0, 0));
    
    // ===== ADICIONAR TODOS OS COMPONENTES AO VBox =====
    def.getChildren().addAll(
        titulo,
        setorLabel, setorCombo,
        maquinaLabel, maquinasCombo,
        prioridadeLabel, prioridadeCombo,
        observacaoLabel, observacaoField,
        botoesBox,
        mensagemLabel
    );
    
    Nova.setScene(nov);
    Nova.show();
});
        
        try {
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("CSS não encontrado: " + e.getMessage());
        }
        
        gerenteStage.setScene(scene);
        gerenteStage.show();
    }

    // REUSABLE SIDEBAR METHOD
    private VBox createSidebar(Stage stage) {

        Image logo = new Image(getClass().getResource("/Imagens/logo.jpg").toExternalForm());
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(120);
        logoView.setPreserveRatio(true);

        VBox sidebar = new VBox();
        sidebar.setPrefWidth(200);
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.getStyleClass().add("sidebar");

        Button btnServico = new Button("Serviços");
        Button btnConfig = new Button("Configurações");
        Button btnSair = new Button("Sair");

        btnServico.getStyleClass().add("sidebar-button");
        btnConfig.getStyleClass().add("sidebar-button");
        btnSair.getStyleClass().add("sidebar-button");

        btnSair.setOnAction(e -> stage.close());

        sidebar.getChildren().addAll(logoView, btnServico, btnConfig, btnSair);

        return sidebar;
    }

    public static void main(String[] args) {

        String url = "jdbc:sqlite:dados.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Connection with database is okay");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        launch(args);
    }
}
