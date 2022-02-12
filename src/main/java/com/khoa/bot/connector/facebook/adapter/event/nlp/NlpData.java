package com.khoa.bot.connector.facebook.adapter.event.nlp;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class NlpData {
    private static final String GREETING = "wit$greetings";
    private static final String THANKS = "wit$thanks";

    private Map<String, List<NlpEntityResult>> entities;

    public Map<String, List<NlpEntityResult>> getEntities() {
        return entities;
    }

    public void setEntities(Map<String, List<NlpEntityResult>> entities) {
        this.entities = entities;
    }

    public boolean ifGreeting() {
        if (Objects.nonNull(entities)) {
            return entities.containsKey(GREETING);
        }

        return false;
    }

    public boolean ifThanks() {
        if (Objects.nonNull(entities)) {
            return entities.containsKey(GREETING);
        }
        return false;
    }
}
