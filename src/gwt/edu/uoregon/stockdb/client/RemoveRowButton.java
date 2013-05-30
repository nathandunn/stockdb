package edu.uoregon.stockdb.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

/**
 */
public class RemoveRowButton extends Button {

    private int rowId;
    private final ExperimentTable parentTable ;

    public RemoveRowButton(int row, ExperimentTable parentTable){
        super("Remove") ;
        this.rowId = row ;
        this.parentTable = parentTable ;

        addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                int rowIndex = getParentTable().findRowId(getRowId());
                if(rowIndex>0){
                    getParentTable().removeRow(rowIndex);
                }
            }
        });
    }

    public int getRowId() {
        return rowId;
    }

    public final ExperimentTable getParentTable() {
        return parentTable;
    }
}
