package view.action.order;

import com.senla.carservice.controller.OrderController;
import org.springframework.beans.factory.annotation.Autowired;

public class ExportOrdersAction extends AbstractOrderAction {
    @Autowired
    private OrderController controller;
    @Override
    public void execute() {
        controller.exportToCsv();

    }

}
