package com.project.controller.dashboard.user;

import com.project.controller.Controller;
import com.project.dao.UserDAO;
import com.project.model.User;
import com.project.router.DashboardRouter;
import com.project.utils.DatabaseUtils;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class DashboardAddUserController extends Controller {
    @FXML
    private MFXTextField usernameField;
    @FXML
    private MFXTextField passwordField;
    @FXML
    private VBox vBoxMain;
    @FXML
    private MFXCheckbox isAdminCb;
    private StackPane stackPaneMain;
    private DashboardRouter dashboardRouter;

    public void setStackPane(StackPane stackPaneMain) {
        this.stackPaneMain = stackPaneMain;
    }

    @FXML
    private void initialize() {
        dashboardRouter = DashboardRouter.getInstance(stackPaneMain);
    }

    @FXML
    private void onSubmit() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean isAdmin = isAdminCb.isSelected();

        UserDAO userDAO = new UserDAO(DatabaseUtils.getConnection());
        userDAO.add(new User("", username, password, isAdmin));

        goBack();
    }

    private void goBack() {
        DashboardUserController controller = (DashboardUserController) dashboardRouter.navigate("dashboard/user/DashboardUser.fxml");
        controller.setStackPane(stackPaneMain);
    }
}
