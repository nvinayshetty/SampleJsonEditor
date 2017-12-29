import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class UserActionListener extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        new JsonEditorDialog(e.getProject()).show();
    }
}
