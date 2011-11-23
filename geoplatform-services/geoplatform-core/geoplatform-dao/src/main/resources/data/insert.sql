-------------
-- Account --
-------------
INSERT INTO gp_account(
            gp_account_type, id, account_non_expired, account_temporary, 
            creation_date, defaultproject_id, is_enabled, email_address, 
            "name", user_password, send_email, user_name, app_id)
    VALUES ('GPUser', 50, true, false, 
            '2011-11-23 11:06:54.004', null, true, 'admin@test.foo', 
            'Complete name of admin', '66C04dk7RhP+SR7kkfl1Hg==', true, 'admin', null);

INSERT INTO gp_account(
            gp_account_type, id, account_non_expired, account_temporary, 
            creation_date, defaultproject_id, is_enabled, email_address, 
            "name", user_password, send_email, user_name, app_id)
    VALUES ('GPUser', 51, true, false, 
            '2011-11-23 11:06:54.108', null, true, 'user@test.foo', 
            'Complete name of user', '3bpykEs9BFCGLQSkVqMDuQ==', true, 'user', null);
INSERT INTO gp_account(
            gp_account_type, id, account_non_expired, account_temporary, 
            creation_date, defaultproject_id, is_enabled, email_address, 
            "name", user_password, send_email, user_name, app_id)
    VALUES ('GPUser', 52, true, false, 
            '2011-11-23 11:06:54.134', null, true, 'viewer@test.foo', 
            'Complete name of viewer', 'guKQmWD6a0MlJAyEnxV7Hw==', true, 'viewer', null);

---------------
-- Authority --
---------------
INSERT INTO gp_authority(
            id, authority, string_id, account_id)
    VALUES (50, 'Admin', 'admin', 50);
INSERT INTO gp_authority(
            id, authority, string_id, account_id)
    VALUES (51, 'User', 'user', 51);
INSERT INTO gp_authority(
            id, authority, string_id, account_id)
    VALUES (52, 'Viewer', 'viewer', 52);
