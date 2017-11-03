package br.com.sport.converter;

import br.com.sport.DAO.TimeDAO;
import br.com.sport.model.Time;

import javax.faces.bean.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.List;

@FacesConverter("timeConverter")
public class TimeConverter implements Converter {

    private List<Time> times = new TimeDAO().findAll();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        for (Time time : times) {
            if (time.getId().toString().equals(value)) {
                return time;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value.equals("")){
            return null;
        }
        Time time = (Time) value;
        if(time != null){
            return String.valueOf(time.getId());
        }else{
            return null;
        }

    }
}
