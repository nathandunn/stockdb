package edu.uoregon.stockdb.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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

    private final String STRAIN_KEY = "strain";
    private final String VALUE_KEY = "value";
    private final String CATEGORY_KEY = "category";
    private final String EXPERIMENTS_KEY = "experiments";
    private final String CATEGORIES_KEY = "categories";
    private final String STRAINS_KEY = "strains";

    private Button addButton = new Button("Add");
    private ListBox strainList = new ListBox();
    private TextBox valueBox = new TextBox();
    private ListBox categoryList = new ListBox();

    private final String ROW_HEIGHT = "20px";

    public ExperimentTable() {
        setCellPadding(0);
        setCellSpacing(0);
//        addStyleName("quick-entry-table");
        setStylePrimaryName("quick-entry-table");
        setWidth("70%");
    }

    public void udpateTable(JSONValue value) {
        clear();

        createHeaders();

        JSONObject measuredValueDto = value.isObject();

        JSONArray strains = measuredValueDto.get(STRAINS_KEY).isArray();
        for (int i = 0; i < strains.size(); i++) {
            strainList.addItem(strains.get(i).isString().stringValue());
        }

        JSONArray categories = measuredValueDto.get(CATEGORIES_KEY).isArray();
        for (int i = 0; i < categories.size(); i++) {
            categoryList.addItem(categories.get(i).isString().stringValue());
        }

        JSONArray experiments = measuredValueDto.get(EXPERIMENTS_KEY).isArray();

        GWT.log("number of experiments: " + experiments.size());

        for (int i = 0; i < experiments.size(); i++) {
            JSONObject experiment = experiments.get(i).isObject();

            createRow(numberRows, experiment.get(STRAIN_KEY).isString().stringValue()
                    , experiment.get(VALUE_KEY).isString().stringValue()
                    , experiment.get(CATEGORY_KEY).isString().stringValue()
            );

            ++numberRows;
        }


        createFooters();
//
//        setCellFormatter(new CellFormatter(){
//            @Override
//            public String getStyleName(int row, int column) {
//                return "quick-entry-table" ;
//            }
//        });

        // for all cells set style name
        for(int col = 0 ; col < 4 ; col++){
            for(int row = 0 ; row < getRowCount() ; ++row){
                getCellFormatter().setStyleName(row,col,"flexTable");
            }
        }

    }



//        ListBox strainList = new ListBox();
//        int selectedStrainIndex = 0 ;
//        for(int i = 0 ; i < this.strainList.getItemCount() ; i++){
//            strainList.addItem(this.strainList.getItemText(i));
//            if(this.strainList.getItemText(i).equals(strain)){
//                selectedStrainIndex=i ;
//            }
//        }
//        strainList.setSelectedIndex(selectedStrainIndex);

//        setWidget(numberRows, STRAIN_COLUMN, strainList);


    private void createRow(int numberRows, String strain, String value, String category) {
        TextBox strainBox = new TextBox();
        strainBox.setText(strain);
        strainBox.setHeight(ROW_HEIGHT);
        strainBox.setStyleName("quick-entry-table");
        setWidget(numberRows, STRAIN_COLUMN, strainBox);

        TextBox valueBox = new TextBox();
        valueBox.setText(value);
        valueBox.setHeight(ROW_HEIGHT);
        valueBox.setStylePrimaryName("quick-entry-table");
        setWidget(numberRows, VALUE_COLUMN, valueBox);

        TextBox categoryBox = new TextBox();
        categoryBox.setText(category);
        categoryBox.setHeight(ROW_HEIGHT);
        categoryBox.setStylePrimaryName("quick-entry-table");
        setWidget(numberRows, CATEGORY_COLUMN, categoryBox);

        RemoveRowButton removeButton = new RemoveRowButton(numberRows, this);
        removeButton.setHeight(ROW_HEIGHT);
        removeButton.setStylePrimaryName("quick-entry-table");

        setWidget(numberRows, ACTION_COLUMN, removeButton);
        getRowFormatter().setStylePrimaryName(numberRows,"quick-entry-table");


    }

    private void addNewRow() {
//        GWT.log("row count : " + getRowCount()) ;
        insertRow(getRowCount() - 1);
//        GWT.log("2 row count : " + getRowCount()) ;
        int insertRow = getRowCount() - 2;

//
        createRow(insertRow,
                strainList.getItemText(strainList.getSelectedIndex())
                , valueBox.getText()
                , categoryList.getItemText(categoryList.getSelectedIndex())
        );

        ++numberRows;
    }

    private void clearNewEntry() {
        strainList.setSelectedIndex(0);
        categoryList.setSelectedIndex(0);
        valueBox.setText("");
    }

    private void createFooters() {
        setWidget(numberRows, STRAIN_COLUMN, strainList);
        setWidget(numberRows, VALUE_COLUMN, valueBox);
        setWidget(numberRows, CATEGORY_COLUMN, categoryList);
        setWidget(numberRows, ACTION_COLUMN, addButton);

        addButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                addNewRow();
                clearNewEntry();
            }


        });

        ++numberRows;
    }

    private void createHeaders() {
        setHTML(numberRows, STRAIN_COLUMN, "<b>Strain</b>");
        setHTML(numberRows, VALUE_COLUMN, "<b>Value</b>");
        setHTML(numberRows, CATEGORY_COLUMN, "<b>Category</b>");
        setHTML(numberRows, ACTION_COLUMN, "<b>Action</b>");
        ++numberRows;
    }


    public int findRowId(int rowId) {
        for (int i = 1; i < getRowCount() - 1; i++) {
            if (rowId == ((RemoveRowButton) getWidget(i, ACTION_COLUMN)).getRowId()) {
                return i;
            }
        }
        return -1;
    }
}
