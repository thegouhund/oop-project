package com.project.controller.dashboard.user;

import com.project.controller.Controller;
import com.project.dao.UserDAO;
import com.project.model.User;
import com.project.model.Ticket;
import com.project.model.User;
import com.project.router.DashboardRouter;
import com.project.utils.DatabaseUtils;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Optional;

public class DashboardUserController extends Controller {
    @FXML
    private MFXTableView<User> tblUser;
    @FXML
    private StackPane stackPaneMain;
    private DashboardRouter dashboardRouter;
    Connection conn = DatabaseUtils.getConnection();
    UserDAO userDAO = new UserDAO(conn);

    @FXML
    public void initialize() {
        dashboardRouter = DashboardRouter.getInstance(stackPaneMain);
        ArrayList<User> users = userDAO.getAll();
        ObservableList<User> tickets = FXCollections.observableArrayList(users);

        MFXTableColumn<User> usernameColumn = new MFXTableColumn<>("Username" );
        MFXTableColumn<User> passwordColumn = new MFXTableColumn<>("Password", true);
        MFXTableColumn<User> isAdminColumn = new MFXTableColumn<>("Admin", false);

        usernameColumn.setRowCellFactory(device -> new MFXTableRowCell<>(User::getUsername));
        passwordColumn.setRowCellFactory(device -> new MFXTableRowCell<>(User::getPassword));
        isAdminColumn.setRowCellFactory(device -> new MFXTableRowCell<>(User::isAdmin));

        tblUser.getTableColumns().add(usernameColumn);
        tblUser.getTableColumns().add(passwordColumn);
        tblUser.getTableColumns().add(isAdminColumn);
        tblUser.setItems(tickets);
    }

    public void setStackPane(StackPane stackPaneMain) {
        this.stackPaneMain = stackPaneMain;
    }

    public void switchToAdd() {
        DashboardAddUserController controller = (DashboardAddUserController) dashboardRouter.navigate("dashboard/user/AddUser.fxml" );
        controller.setStackPane(stackPaneMain);
    }

    public void switchToEdit() {
//        stackPaneMain.setStyle("-fx-background-color: #fff");
//        DashboardEditUserController controller = (DashboardEditUserController) dashboardRouter.navigate("dashboard/user/EditUser.fxml");
//        controller.setStackPane(stackPaneMain);
//        System.out.println(tblUser.getSelectionModel().getSelectedValue());
//        controller.setSelectedUser(tblUser.getSelectionModel().getSelectedValue());

        User selectedUser = tblUser.getSelectionModel().getSelectedValue();
        System.out.println(selectedUser);
        if (selectedUser != null) {
            DashboardEditUserController controller = (DashboardEditUserController) dashboardRouter.navigate("dashboard/user/EditUser.fxml" );
            controller.setStackPane(stackPaneMain);
            controller.setSelectedUser(tblUser.getSelectionModel().getSelectedValue());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error" );
            alert.setHeaderText("Pilih Akun" );
            alert.setContentText("Pilih akun yang ingin diubah!" );
            alert.showAndWait();
        }
    }

    @FXML
    public void deleteSelected() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi" );
        alert.setHeaderText("Konfirmasi" );
        alert.setContentText("Apakah anda ingin menghapus?" );

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            userDAO.delete(tblUser.getSelectionModel().getSelectedValue().getId());
            DashboardUserController controller = (DashboardUserController) dashboardRouter.navigate("dashboard/user/DashboardUser.fxml" );
            controller.setStackPane(stackPaneMain);
        }
    }

}
