package tv.mundord.beans;

/**
 * Created by jonathan on 18/12/2014.
 */
public class Menu_DTO {
    private String name;
    private int icon;

    public Menu_DTO(){}

    public Menu_DTO(String name, int icon){
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
