package org.mule.extension.internal;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SlackMessage {

    private String type;
    private String channel;
    private String user;
    private String text;
    private String ts;
    private String source_team;
    private String team;
    private String client_msg_id;
    private String event_ts;

    public String getType() {
        return type;
    }

    public String getChannel() {
        return channel;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public String getTs() {
        return ts;
    }

    public String getSource_team() {
        return source_team;
    }

    public String getTeam() {
        return team;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public void setSource_team(String source_team) {
        this.source_team = source_team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getClient_msg_id() {
        return client_msg_id;
    }

    public void setClient_msg_id(String client_msg_id) {
        this.client_msg_id = client_msg_id;
    }

    public String getEvent_ts() {
        return event_ts;
    }

    public void setEvent_ts(String event_ts) {
        this.event_ts = event_ts;
    }
}
