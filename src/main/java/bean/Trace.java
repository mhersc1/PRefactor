package bean;

//It's better to use lombok ...
//for getters, setters, builder and others ...
public class Trace {
    private String messageText;
    private Integer type;
    private transient boolean isValid;

    private Trace(String messageText, Integer type, boolean isValid) {
        this.messageText = messageText;
        this.type = type;
        this.isValid = isValid;
    }


    public String getMessageText() {
        return messageText;
    }

    public Integer getType() {
        return type;
    }

    public boolean isValid() {
        return isValid;
    }

    public void changeMessageTxt(String newMessage) {
        this.messageText = newMessage;
    }

    public void changeState(boolean isValid) {
        this.isValid = isValid;
    }

    public static class Builder {
        private String messageText;
        private Integer type;

        public Builder() {
        }

        public Builder messageText(String messageText) {
            String message = "";
            if (messageText != null) {
                message = messageText.trim();
            }
            this.messageText = message;
            return this;
        }

        public Builder type(Integer type) {
            this.type = type;
            return this;
        }

        public Trace build() {
            return new Trace(this.messageText, this.type, false);
        }
    }
}
