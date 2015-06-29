package by.pavel;

public enum View {

    HOME(""), LOGIN, CONTACT, REGISTRATION, SETTINGS, TRAVEL, ADMIN;

    private String externalAddress;

    View(String externalAddress) {
        this.externalAddress = externalAddress;
    }

    View() {
        this.externalAddress = name().toLowerCase();
    }

    public String getExternalAddress() {
        return externalAddress;
    }

    public String getInternalAddress() {
        return "/pages/" + name().toLowerCase() + ".jsp";
    }
}
