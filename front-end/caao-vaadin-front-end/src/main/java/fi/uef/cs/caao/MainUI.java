package fi.uef.cs.caao;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {


        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        VerticalLayout settingsItems = new VerticalLayout();
        VerticalLayout selection = new VerticalLayout();

        Tree tree = new Tree("Settings items");

        tree.addItem("User list");
        tree.addItem("Location list");
        tree.addItem("Languages");
        tree.addItem("Sensors list");
        tree.addItem("Map");
        HorizontalSplitPanel hlSplitPanel = new HorizontalSplitPanel();
        setContent(hlSplitPanel);

        Table table = new Table();

        setContent(hlSplitPanel);
        hlSplitPanel.addComponent(tree);
        hlSplitPanel.addComponent(table);
    }

}
