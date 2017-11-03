package br.com.sport.converter;

import br.com.sport.DAO.CupomDAO;
import br.com.sport.model.Cupom;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.List;

@FacesConverter("cupomConverter")
public class CupomConverter implements Converter {

    private List<Cupom> cupomList = new CupomDAO().findAll();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        for (Cupom cupom : cupomList) {
            if (cupom.getId().toString().equals(value)) {
                return cupom;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value.equals("")){
            return null;
        }
        Cupom cupom = (Cupom) value;
        return String.valueOf(cupom.getId());
    }
}
