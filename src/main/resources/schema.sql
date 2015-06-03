CREATE TABLE IF NOT EXISTS damage_report_mail (
  gmail_id varchar(255) PRIMARY KEY NOT NULL,
  message_id varchar(255) NOT NULL,
  opposite_agent_name varchar(255),
  attack_date datetime,
  create_date datetime
);
CREATE TABLE IF NOT EXISTS damage_portal (
  message_id varchar(255) NOT NULL,
  seq integer NOT NULL,
  portal_name varchar(255),
  latitude float,
  longitude float,
  portal_intel_url varchar(255),
  create_date datetime,
  PRIMARY KEY(message_id, seq)
);
CREATE UNIQUE INDEX IF NOT EXISTS message_id_uniq_idx ON damage_report_mail (message_id ASC);
