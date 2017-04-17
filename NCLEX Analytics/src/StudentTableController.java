import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class StudentTableController {

    @FXML
    private TextField filterField;
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;
    @FXML
    private TableColumn<Student, String> cohortColumn;

    private ObservableList<Student> masterData = FXCollections.observableArrayList();


    public StudentTableController() {
        masterData.add(new Student("Hans", "Muster", "acc"));
        masterData.add(new Student("Ruth", "Mueller", "acc"));
        masterData.add(new Student("Heinz", "Kurz", "acc"));
        masterData.add(new Student("Cornelia", "Meier", "acc"));
        masterData.add(new Student("Werner", "Meyer", "acc"));
        masterData.add(new Student("Lydia", "Kunz", "acc"));
        masterData.add(new Student("Anna", "Best", "acc"));
        masterData.add(new Student("Stefan", "Meier", "acc"));
        masterData.add(new Student("Martin", "Mueller", "acc"));
    }


    @FXML
    private void initialize() {
        // 0. Initialize the columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        //cohortColumn.setCellValueFactory(cellData -> cellData.getValue().cohortProperty());
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Student> filteredData = new FilteredList<>(masterData, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Student -> {
                // If filter text is empty, display all Students.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every Student with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Student.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (Student.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Student> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(studentTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        studentTable.setItems(sortedData);
    }
}