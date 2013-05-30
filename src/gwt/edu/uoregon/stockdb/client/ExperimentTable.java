package edu.uoregon.stockdb.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 */
public class ExperimentTable extends FlexTable {

    int numberRows = 0;

    private final int STRAIN_COLUMN = 0;
    private final int VALUE_COLUMN = 1;
    private final int CATEGORY_COLUMN = 2;
    private final int ACTION_COLUMN = 3;

    private Button addButton = new Button("Add");
    private ListBox strainList = new ListBox();
    private TextBox valueBox = new TextBox();
    private ListBox categoryList = new ListBox();

    public void udpateTable(JSONValue value) {
        clear();
        createHeaders();

        JSONArray experiments = value.isArray();
        GWT.log("number of experiments: "+experiments.size());

        for (int i = 0; i < experiments.size(); i++) {
            JSONObject experiment = experiments.get(i).isObject();

            TextBox strainBox = new TextBox();
            strainBox.setText(experiment.get("strain").isString().stringValue());
            setWidget(numberRows, STRAIN_COLUMN, strainBox);


            TextBox valueBox = new TextBox();
            valueBox.setText(experiment.get("value").isString().stringValue());
            setWidget(numberRows, VALUE_COLUMN, valueBox);
//
            TextBox categoryBox = new TextBox();
            categoryBox.setText(experiment.get("category").isString().stringValue());
            setWidget(numberRows, CATEGORY_COLUMN, categoryBox );

            setWidget(numberRows, ACTION_COLUMN, new Button("Remove"));

            ++numberRows;
        }


        createFooters();
    }

    private void createFooters() {
        setWidget(numberRows, STRAIN_COLUMN, strainList);
        setWidget(numberRows, VALUE_COLUMN, valueBox);
        setWidget(numberRows, CATEGORY_COLUMN, categoryList);
        setWidget(numberRows, ACTION_COLUMN, addButton);
        ++numberRows;
    }

    private void createHeaders() {
        setHTML(numberRows, STRAIN_COLUMN, "<b>Strain</b>");
        setHTML(numberRows, VALUE_COLUMN, "<b>Value</b>");
        setHTML(numberRows, CATEGORY_COLUMN, "<b>Category</b>");
        setHTML(numberRows, ACTION_COLUMN, "<b>Action</b>");
        ++numberRows;
    }


}
