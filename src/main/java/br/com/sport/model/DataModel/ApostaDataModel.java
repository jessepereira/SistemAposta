package br.com.sport.model.DataModel;

import br.com.sport.model.Aposta;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

public class ApostaDataModel extends ListDataModel<Aposta> implements SelectableDataModel<Aposta> {

    public ApostaDataModel() {

    }

    public ApostaDataModel(List<Aposta> data) {
        super(data);
    }

    @Override
    public Object getRowKey(Aposta a) {
        return a.getId();
    }

    @Override
    public Aposta getRowData(String id) {
        List<Aposta> apostas = (List<Aposta>) getWrappedData();
        for (Aposta aposta : apostas) {
            if (aposta.getId() == (Integer.parseInt(id)))
                return aposta;
        }
        return null;
    }
}
