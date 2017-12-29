import com.intellij.json.JsonLanguage;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.highlighter.EditorHighlighterFactory;
import com.intellij.openapi.editor.impl.EditorImpl;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class JsonEditorDialog extends DialogWrapper {

    @Nullable
    private Project project;
    private EditorImpl editor;

    protected JsonEditorDialog(@Nullable Project project) {
        super(project);
        this.project = project;
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel contentPane = new JPanel();
        setSize(900, 900);
        Language language = Language.findLanguageByID(JsonLanguage.INSTANCE.getID());
        FileType fileType = language != null ? language.getAssociatedFileType() : null;
        Document myDocument = EditorFactory.getInstance().createDocument("");

        editor = (EditorImpl) EditorFactory.getInstance().createEditor(myDocument);
        if (fileType != null) {
            editor.setHighlighter(EditorHighlighterFactory.getInstance().createEditorHighlighter(project, fileType));
        }
        editor.getSettings().setLineNumbersShown(true);
        editor.getSettings().setBlinkCaret(true);
        editor.getSettings().setFoldingOutlineShown(false);
        editor.getFoldingModel().setFoldingEnabled(false);
        editor.getSettings().setAutoCodeFoldingEnabled(true);

        editor.getComponent().setPreferredSize(new Dimension(800, 800));
        contentPane.add(editor.getComponent());
        return contentPane;
    }


}
