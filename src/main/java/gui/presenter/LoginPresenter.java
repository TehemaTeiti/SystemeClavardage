package gui.presenter;

import gui.view.ChooseUserFrame;
import gui.view.LoginListener;

public class LoginPresenter implements LoginListener {

    private LoginView view;

    public void setView(LoginView view) {
        this.view = view;
    }

    @Override
    public void onClickButton(String text) {
        view.dispose();
        ChooseUserPresenter presenter = new ChooseUserPresenter(text);
        ChooseUserView view = new ChooseUserFrame(presenter);
        presenter.setView(view);
    }
}
