package com.project.controller.dashboard.user;

import com.project.controller.Controller;
import com.project.router.DashboardRouter;
import com.project.dao.UserDAO;
import com.project.model.User;
import com.project.utils.DatabaseUtils;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;


public class DashboardEditUserController extends Controller {
    private User selectedUser;
    private StackPane stackPaneMain;
    @FXML
    private MFXTextField usernameField;
    @FXML
    private MFXTextField passwordField;
    @FXML
    private MFXCheckbox isAdminCb;

    private DashboardRouter dashboardRouter;

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
        this.usernameField.setText(selectedUser.getUsername());
        this.passwordField.setText(selectedUser.getPassword());
    }

    @FXML
    private void initialize() {
        dashboardRouter = DashboardRouter.getInstance(stackPaneMain);
        System.out.println(selectedUser);
    }

    @FXML
    private void onSubmit() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean isAdmin = isAdminCb.isSelected();

        UserDAO userDao = new UserDAO(DatabaseUtils.getConnection());
        userDao.update(new User("", username, password, isAdmin), selectedUser.getId());

        goBack();
    }

    private void goBack() {
        DashboardUserController controller = (DashboardUserController) dashboardRouter.navigate("dashboard/user/DashboardUser.fxml");
        controller.setStackPane(stackPaneMain);
    }

    public void setStackPane(StackPane stackPaneMain) {
        this.stackPaneMain = stackPaneMain;
    }
}
