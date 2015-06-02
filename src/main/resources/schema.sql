CREATE TABLE IF NOT EXISTS damage_report_mail (
  message_id varchar(255) PRIMARY KEY NOT NULL,
  oppsite_agent_name varchar(255),
  attack_date datetime,
  create_date datetime
);
CREATE TABLE IF NOT EXISTS damage_portal (
  message_id varchar(255) NOT NULL,
  seq integer NOT NULL,
  portal_name varchar(255),
  latitude float,
  longitude float,
  porta_lintel_url varchar(255),
  create_date datetime,
  PRIMARY KEY(message_id, seq)
);
