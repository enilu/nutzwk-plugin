package cn.enilu.common.code.plugin.ui;

import cn.enilu.common.code.plugin.utils.GenerateConfig;
import com.intellij.ide.util.DefaultPsiElementCellRenderer;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiModifier;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.components.panels.HorizontalBox;
import com.intellij.ui.components.panels.VerticalBox;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by ghost on 2016/4/1.
 */
public class ConfigDialog extends DialogWrapper {
    private JBCheckBox controllerCheckBox;
    private JBCheckBox serviceCheckBox;
    private JBCheckBox viewCheckBox;

    private JBCheckBox viewAddCheckBox;
    private JBCheckBox viewDetailCheckBox;
    private JBCheckBox viewEditCheckBox;
    private JBCheckBox viewIndexCheckBox;

    private JBCheckBox forceCheckBox;

    private JBTextField baseUriTextField;
    private JBTextField basePackageTextField;
    private final PsiClass mClass;
    private final String basePackage;
    private final String baseUri;

    public ConfigDialog(final PsiClass psiClass) {
        super(psiClass.getProject());
        String arr[] = psiClass.getQualifiedName().split("\\.");
        String modelName = psiClass.getName();

        basePackage = psiClass.getQualifiedName().replace("."+arr[arr.length-2]+"."+arr[arr.length-1],"");
        baseUri = "/private/"+basePackage.substring(basePackage.lastIndexOf(".")+1);
        mClass = psiClass;
        setupViews(modelName);
        init();
    }


    private void setupViews(String modelName) {

        setTitle("Generate Model:"+modelName);

        controllerCheckBox = new JBCheckBox("controllers",true);
        serviceCheckBox = new JBCheckBox("services",true);
        viewCheckBox = new JBCheckBox("views",true);

        viewAddCheckBox = new JBCheckBox("add",true);
        viewDetailCheckBox = new JBCheckBox("detail",true);
        viewEditCheckBox = new JBCheckBox("edit",true);
        viewIndexCheckBox = new JBCheckBox("index",true);

        forceCheckBox = new JBCheckBox("replace",false);

        baseUriTextField = new JBTextField(baseUri);
        basePackageTextField = new JBTextField(basePackage);
        viewCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(viewCheckBox.isSelected()){

                    viewAddCheckBox.setSelected(true);
                    viewDetailCheckBox.setSelected(true);
                    viewEditCheckBox.setSelected(true);
                    viewIndexCheckBox.setSelected(true);

                    viewAddCheckBox.setVisible(true);
                    viewDetailCheckBox.setVisible(true);
                    viewEditCheckBox.setVisible(true);
                    viewIndexCheckBox.setVisible(true);
                }else{
                    viewAddCheckBox.setSelected(false);
                    viewDetailCheckBox.setSelected(false);
                    viewEditCheckBox.setSelected(false);
                    viewIndexCheckBox.setSelected(false);

                    viewAddCheckBox.setVisible(false);
                    viewDetailCheckBox.setVisible(false);
                    viewEditCheckBox.setVisible(false);
                    viewIndexCheckBox.setVisible(false);
                }
            }
        });
    }

    @Nullable
    @Override
    protected JComponent createSouthPanel() {
        JComponent southPanel = super.createSouthPanel();
        if (null == southPanel) {
            return null;
        }
        final VerticalBox root = new VerticalBox();
        root.add(baseUriTextField);
        root.add(basePackageTextField);
        root.add(forceCheckBox);
        root.add(southPanel);
        return root;
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JComponent centerPanel = super.createContentPane();
        final VerticalBox verticalBox = new VerticalBox();
        final HorizontalBox  horizontalBox1 = new HorizontalBox();

        final HorizontalBox  horizontalBox2 = new HorizontalBox();

        horizontalBox1.add(controllerCheckBox);
        horizontalBox1.add(serviceCheckBox);
        horizontalBox1.add(viewCheckBox);

        horizontalBox2.add(viewAddCheckBox);
        horizontalBox2.add(viewDetailCheckBox);
        horizontalBox2.add(viewEditCheckBox);
        horizontalBox2.add(viewIndexCheckBox);
        verticalBox.add(horizontalBox1);
        verticalBox.add(horizontalBox2);
        centerPanel.add(verticalBox);
        return centerPanel;
    }

    public GenerateConfig getGenerateConfig(){
        GenerateConfig config = new GenerateConfig();
        config.setBasePackage(basePackage);
        config.setBaseUri(baseUriTextField.getText().trim());
        config.setConroller(controllerCheckBox.isSelected());
        config.setService(serviceCheckBox.isSelected());
        config.setView(viewCheckBox.isSelected());
        config.setForce(forceCheckBox.isSelected());
        StringBuilder pages = new StringBuilder();
        pages.append(viewIndexCheckBox.isSelected()?"index_":"").append(viewAddCheckBox.isSelected()?"add_":"")
                .append(viewDetailCheckBox.isSelected()?"detail_":"").append(viewEditCheckBox.isSelected()?"edit_":"");
        config.setPages(pages.toString());
        return config;
    }

}
