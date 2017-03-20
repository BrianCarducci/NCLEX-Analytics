import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


public class Main extends Application implements EventHandler<ActionEvent>{

	private Button browseButton, homeButton;
	private Label title;
	private Stage window;
	private Scene scene, studentScene;
	private BorderPane layout, studentLayout;
	private TableView<Assessment> studentTable;

	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		primaryStage.setTitle("NCLEX Analytics");

		homeButton = new Button("home");
		browseButton = new Button("Browse...");

		layout = new BorderPane();
		layout.setTop(addHBox(primaryStage));
		layout.setLeft(addFlowPane(primaryStage));

		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("StudentTable.fxml"));
			AnchorPane tablePane = (AnchorPane) loader.load();
			layout.setCenter(tablePane);
		} catch (IOException e) {
			e.printStackTrace();
		}

		studentLayout = new BorderPane();

		TableColumn<Assessment, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<Assessment, String>("assessmentName"));

		TableColumn<Assessment, Double> scoreColumn = new TableColumn<>("Score");
		scoreColumn.setCellValueFactory(new PropertyValueFactory<Assessment, Double>("score"));

		TableColumn<Assessment, Double> focusColumn = new TableColumn<>("Focus Review Time");
		focusColumn.setCellValueFactory(new PropertyValueFactory<Assessment, Double>("focusReviewTime"));

		studentTable = new TableView<>();
		studentTable.setItems(getAssessments());
		studentTable.getColumns().addAll(nameColumn, scoreColumn, focusColumn);

		studentLayout.setTop(addHBox(primaryStage));
		studentLayout.setCenter(studentTable);
		studentScene = new Scene(studentLayout);

		scene = new Scene(layout, 1000, 500);

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	@Override
	public void handle(ActionEvent event) {
//		if (event.getSource() == button) {
//			System.out.println("button clicked");
//		}

	}

	public HBox addHBox(Stage stage) {
	    HBox hbox = new HBox();

	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");

	    title = new Label();
	    title.setText("NCLEX Analytics");

	    Button homeButton = new Button("Home");
	    homeButton.setPrefSize(100, 20);
	    homeButton.setOnAction(e -> {
	    	title.setText("NCLEX Analytics");
	    	stage.setScene(scene);
	    });

	    Button button2 = new Button("button 2");
	    button2.setPrefSize(100, 20);
	    hbox.getChildren().addAll(homeButton, button2, title);

	    return hbox;
	}

	public FlowPane addFlowPane(Stage stage) {
		FlowPane flowPane = new FlowPane();

		Button browseButton = new Button("Browse...");
		browseButton.setPrefSize(70, 20);
		browseButton.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select an Excel Folder");
			fileChooser.showOpenDialog(stage);
		});
		Button studentButton = new Button("Student Screen");
		studentButton.setPrefSize(70, 20);
		studentButton.setOnAction(e -> {
			title.setText("Student Name");
			stage.setScene(studentScene);
		});
		flowPane.getChildren().addAll(browseButton, studentButton);
		return flowPane;
	}

	public ObservableList<Assessment> getAssessments() {
		ObservableList<Assessment> assessments = FXCollections.observableArrayList();
		assessments.add(new Assessment("assessment 1", 100, 2));
		assessments.add(new Assessment("assessment 2", 100, 2));
		assessments.add(new Assessment("assessment 3", 100, 2));
		assessments.add(new Assessment("assessment 4", 100, 2));
		return assessments;
	}
}