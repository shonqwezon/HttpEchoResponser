package org.semyonq;

public class User {
    private int id;
    private String name;
    private String company;
    private String username;
    private String email;
    private String address;
    private String zip;
    private String state;
    private String country;
    private String phone;
    private String photo;

    @Override
    public String toString() {
        return "User {" +
                "\n\tid\t\t= " + id +
                ",\n\tname\t= '" + name + '\'' +
                ",\n\tcompany\t= '" + company + '\'' +
                ",\n\tusername= '" + username + '\'' +
                ",\n\temail\t= '" + email + '\'' +
                ",\n\taddress\t= '" + address + '\'' +
                ",\n\tzip\t\t= '" + zip + '\'' +
                ",\n\tstate\t= '" + state + '\'' +
                ",\n\tcountry\t= '" + country + '\'' +
                ",\n\tphone\t= '" + phone + '\'' +
                ",\n\tphoto\t= '" + photo + '\'' +
                "\n}";
    }

    public User toUser(String jsonString) throws BadKeyException {
        try {
            jsonString = jsonString.replace("\"", "") + "}";
            this.id = Integer.parseInt(getValue("id:", jsonString));
            this.name = getValue("name:", jsonString);
            this.company = getValue("company:", jsonString);
            this.username = getValue("username:", jsonString);
            this.address = getValue("address:", jsonString);
            this.zip = getValue("zip:", jsonString);
            this.state = getValue("state:", jsonString);
            this.country = getValue("country:", jsonString);
            this.phone = getValue("phone:", jsonString);
            this.photo = getValue("photo:", jsonString);
            return this;
        }
        catch (Exception e) {
            throw new BadKeyException("Невозможно распарсить json");
        }
    }

    private String getValue(String key, String json) {
        int start = json.indexOf(key) + key.length();
        int end = json.indexOf(",", start);
        if (end == -1)
            end = json.indexOf("}", start);
        return json.substring(start, end).trim();
    }
}
