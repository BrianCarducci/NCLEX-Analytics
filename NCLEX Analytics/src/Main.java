import java.awt.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Main extends Application implements EventHandler<ActionEvent> {

	private Button browseButton, homeButton;
	private Label title;
	private Stage window;
	private Scene scene, studentScene;
	private BorderPane layout, chartLayout, studentLayout, graphLayout;
	public TableView<Assessment> studentTable_2;
	private ToggleGroup studentRadioGroup, cohortRadioGroup;
	private RadioButton proctoredButton, practiceButton, juniorButton, accJuniorButton, seniorButton;
	private CheckBox proctoredCheckBox, practiceCheckBox;
	private Stage stage;
	private ObservableList<Assessment> studentAssessments;



	public static Main main;

	public static void main(String[] args) {
		launch(args);
	}

	public void setAssessmentList(ObservableList<Assessment> studentAssessments) {
		this.studentAssessments = studentAssessments;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ListOfStudents s = new ListOfStudents();
		stage = primaryStage;

		main = this;



		window = primaryStage;
		primaryStage.setTitle("NCLEX Analytics");

		homeButton = new Button("home");
		browseButton = new Button("Browse...");


		layout = new BorderPane();
		layout.setTop(addHBox(primaryStage));
		layout.setLeft(addVBox(primaryStage));

		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("StudentTable.fxml"));
			AnchorPane tablePane = (AnchorPane) loader.load();

			layout.setCenter(tablePane);

		} catch (IOException e) {
			e.printStackTrace();
		}



		TableColumn<Assessment, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<Assessment, String>("name"));

		TableColumn<Assessment, String> scoreColumn = new TableColumn<>("Score");
		scoreColumn.setCellValueFactory(new PropertyValueFactory<Assessment, String>("percentage"));

		TableColumn<Assessment, String> focusColumn = new TableColumn<>("Focus Review Time");
		focusColumn.setCellValueFactory(new PropertyValueFactory<Assessment, String>("focusedReviewTime"));

		TableColumn<Assessment, String> dateColumn = new TableColumn<>("Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<Assessment, String>("date"));

		TableColumn<Assessment, String> proficiencyLevelColumn = new TableColumn<>("Proficiency Level");
		proficiencyLevelColumn.setCellValueFactory(new PropertyValueFactory<Assessment, String>("proficiencyLevel"));

		TableColumn<Assessment, String> probOfPassingColumn = new TableColumn<>("Probability of Passing");
		probOfPassingColumn.setCellValueFactory(new PropertyValueFactory<Assessment, String>("probOfPassing"));



		studentTable_2 = new TableView<>();
		studentTable_2.setItems(getAssessments());
		studentTable_2.setPrefSize(1000, 500);
		studentTable_2.getColumns().addAll(nameColumn, dateColumn, scoreColumn, proficiencyLevelColumn, focusColumn,
				probOfPassingColumn);

		studentLayout = new BorderPane();
		studentLayout.setTop(addStudentHBox(stage));
		studentLayout.setCenter(studentTable_2);
		studentLayout.setLeft(addStudentVBox(stage));

//		chartLayout = new BorderPane();
//		chartLayout.setCenter(studentTable_2);
//		chartLayout.setLeft(addStudentVBox(primaryStage));

//		graphLayout = new BorderPane();
//		graphLayout.setCenter(new Label("Graph"));

		studentScene = new Scene(studentLayout);

		scene = new Scene(layout, 1000, 500);

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	@Override
	public void handle(ActionEvent event) {

		if (event.getSource() == studentTable_2) {
			System.out.println("button clicked");
		}

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

	public HBox addStudentHBox(Stage stage) {

		HBox hbox = new HBox();

		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: #336699;");

		Button homeButton = new Button("Home");
		homeButton.setPrefSize(100, 20);
		homeButton.setOnAction(e -> {
			title.setText("NCLEX Analytics");
			stage.setScene(scene);
		});

		ToggleGroup studentSceneToggle = new ToggleGroup();

		RadioButton chartButton = new RadioButton("Chart");
		chartButton.setToggleGroup(studentSceneToggle);
		chartButton.setSelected(true);
		chartButton.setOnAction(e -> {
			studentLayout.setCenter(studentTable_2);
		});

		RadioButton graphButton = new RadioButton("Graph");
		graphButton.setToggleGroup(studentSceneToggle);
		graphButton.setOnAction(e -> {
			BarGraph graph = new BarGraph(this.getStage());
			studentLayout.setCenter(graph.getGraph());
		});

//		TabPane tabPane = new TabPane();
//
//		Tab chartTab = new Tab("Chart");
//		chartTab.setContent(chartLayout);
//
//		Tab graphTab = new Tab("Graph");
//
//		chartTab.setContent(studentLayout);
//		graphTab.setContent(graphLayout);
//
//		tabPane.getTabs().addAll(chartTab, graphTab);




		hbox.getChildren().addAll(homeButton, title, chartButton, graphButton);

		return hbox;

	}

	@FXML
	public VBox addVBox(Stage stage) {
		VBox vbox = new VBox();

		Button folderButton = new Button("Browse...");
		folderButton.setPrefSize(70, 20);
		folderButton.setOnAction(e -> {
			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setTitle("Choose folder with excel files");
			File[] files = directoryChooser.showDialog(stage).listFiles();
			ExcelParsing excelParser = new ExcelParsing();
			ObservableList<Student> studentsObs = FXCollections.observableArrayList();

			for (File file : files) {
				try {
					Hashtable <String, Student> students = new Hashtable<String, Student>();
					students = excelParser.parse(file.getPath());
					for (String key : students.keySet()) {
						studentsObs.add(students.get(key));
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			for (Student student : studentsObs) {
				System.out.println(student.getFirstName() + ", " + student.getLastName() + ", " + student.getStudentId() + ", " + student.getCohort());
			}
			StudentTableController.setItems(studentsObs);
			try {
				FileWriter writer = new FileWriter("path.txt");
				writer.write(files[0].getParent());
				writer.write("test");
				writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		});

		Label cohortLabel = new Label("Filter by Cohort:");

		cohortRadioGroup = new ToggleGroup();

		juniorButton = new RadioButton("Junior");
		juniorButton.setToggleGroup(cohortRadioGroup);
		juniorButton.setSelected(false);

		accJuniorButton = new RadioButton("Acclerated Junior");
		accJuniorButton.setToggleGroup(cohortRadioGroup);
		accJuniorButton.setSelected(false);

		seniorButton = new RadioButton("Senior");
		seniorButton.setToggleGroup(cohortRadioGroup);
		seniorButton.setSelected(false);

		vbox.getChildren().addAll(cohortLabel, juniorButton, accJuniorButton, seniorButton, folderButton);
		return vbox;
	}

	public VBox addStudentVBox(Stage stage) {
		VBox vbox = new VBox();

//		studentRadioGroup = new ToggleGroup();
//
//		proctoredButton = new RadioButton("Proctored");
//		proctoredButton.setToggleGroup(studentRadioGroup);
//		proctoredButton.setSelected(false);
//		proctoredButton.setOnAction(e -> {
			studentTable_2.setItems(studentAssessments);
//			ObservableList<Assessment> proctoredAssessments = FXCollections.observableArrayList();
//
//			for (int i = 0; i < studentAssessments.size(); i++) {
//				if (studentAssessments.get(i) instanceof ProctoredAssessment) {
//					proctoredAssessments.add(studentAssessments.get(i));
//				}
//			}
//			studentTable_2.setItems(proctoredAssessments);
//
//		});
//
//		practiceButton = new RadioButton("Practice");
//		practiceButton.setToggleGroup(studentRadioGroup);
//
//		practiceButton.setSelected(false);
//		practiceButton.setOnAction(e -> {
//			studentTable_2.setItems(studentAssessments);
//			ObservableList<Assessment> practiceAssessments = FXCollections.observableArrayList();
//
//			for (int i = 0; i < studentAssessments.size(); i++) {
//				if (studentAssessments.get(i) instanceof PracticeAssessment) {
//					practiceAssessments.add(studentAssessments.get(i));
//				}
//			}
//			studentTable_2.setItems(practiceAssessments);
//
//		});
		proctoredCheckBox = new CheckBox("Proctored");
		proctoredCheckBox.setSelected(true);

		practiceCheckBox = new CheckBox("Practice");
		practiceCheckBox.setSelected(true);

		proctoredCheckBox.setOnAction(e -> {
			if (proctoredCheckBox.isSelected() && practiceCheckBox.isSelected()) {
				studentTable_2.setItems(studentAssessments);
			}
			else if (proctoredCheckBox.isSelected() && !practiceCheckBox.isSelected()) {
				ObservableList<Assessment> proctoredAssessments = FXCollections.observableArrayList();
				for (int i = 0; i < studentAssessments.size(); i++) {
					if (studentAssessments.get(i) instanceof ProctoredAssessment) {
						proctoredAssessments.add(studentAssessments.get(i));
					}
				}
				studentTable_2.setItems(proctoredAssessments);
			}
			else if (!proctoredCheckBox.isSelected() && practiceCheckBox.isSelected()) {
				studentTable_2.setItems(studentAssessments);
				ObservableList<Assessment> practiceAssessments = FXCollections.observableArrayList();
				for (int i = 0; i < studentAssessments.size(); i++) {
					if (studentAssessments.get(i) instanceof PracticeAssessment) {
						practiceAssessments.add(studentAssessments.get(i));
					}
				}
				studentTable_2.setItems(practiceAssessments);
			}
		});

		practiceCheckBox.setOnAction(e -> {
			if (proctoredCheckBox.isSelected() && practiceCheckBox.isSelected()) {
				studentTable_2.setItems(studentAssessments);
			}
			else if (proctoredCheckBox.isSelected() && !practiceCheckBox.isSelected()) {
				ObservableList<Assessment> proctoredAssessments = FXCollections.observableArrayList();
				for (int i = 0; i < studentAssessments.size(); i++) {
					if (studentAssessments.get(i) instanceof ProctoredAssessment) {
						proctoredAssessments.add(studentAssessments.get(i));
					}
				}
				studentTable_2.setItems(proctoredAssessments);
			}
			else if (!proctoredCheckBox.isSelected() && practiceCheckBox.isSelected()) {
				studentTable_2.setItems(studentAssessments);
				ObservableList<Assessment> practiceAssessments = FXCollections.observableArrayList();
				for (int i = 0; i < studentAssessments.size(); i++) {
					if (studentAssessments.get(i) instanceof PracticeAssessment) {
						practiceAssessments.add(studentAssessments.get(i));
					}
				}
				studentTable_2.setItems(practiceAssessments);
			}
		});

		vbox.getChildren().addAll(proctoredCheckBox, practiceCheckBox);
		return vbox;
	}

	public Stage getStage() {
		return stage;
	}

	public Scene getStudentScene() {
		return studentScene;
	}

	public ObservableList<Assessment> getAssessments() {
		ObservableList<Assessment> assessments = FXCollections.observableArrayList();
		/*
		 * assessments.add(new Assessment("assessment 1", 100, 2));
		 * assessments.add(new Assessment("assessment 2", 100, 2));
		 * assessments.add(new Assessment("assessment 3", 100, 2));
		 * assessments.add(new Assessment("assessment 4", 100, 2));
		 */
		return assessments;
	}

	public Label getTitle() {
		return title;
	}

	public void setTitle(String newTitle) {
		title.setText(newTitle);
	}
}