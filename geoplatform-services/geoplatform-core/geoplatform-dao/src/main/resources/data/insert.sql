-------------
-- Account --
-------------
INSERT INTO gp_account(
            gp_account_type, id, account_non_expired, account_temporary, 
            creation_date, default_project_id, is_enabled, email_address, 
            "name", user_password, send_email, user_name, app_id)
    VALUES ('GPUser', 0, true, false, 
            '2011-11-23 11:06:54.004', null, true, 'admin@test.foo', 
            'Complete name of admin', '66C04dk7RhP+SR7kkfl1Hg==', true, 'admin', null);

INSERT INTO gp_account(
            gp_account_type, id, account_non_expired, account_temporary, 
            creation_date, default_project_id, is_enabled, email_address, 
            "name", user_password, send_email, user_name, app_id)
    VALUES ('GPUser', 1, true, false, 
            '2011-11-23 11:06:54.108', null, true, 'user@test.foo', 
            'Complete name of user', '3bpykEs9BFCGLQSkVqMDuQ==', true, 'user', null);
INSERT INTO gp_account(
            gp_account_type, id, account_non_expired, account_temporary, 
            creation_date, default_project_id, is_enabled, email_address, 
            "name", user_password, send_email, user_name, app_id)
    VALUES ('GPUser', 2, true, false, 
            '2011-11-23 11:06:54.134', null, true, 'viewer@test.foo', 
            'Complete name of viewer', 'guKQmWD6a0MlJAyEnxV7Hw==', true, 'viewer', null);

---------------
-- Authority --
---------------
INSERT INTO gp_authority(
            id, authority, string_id, account_id)
    VALUES (0, 'Admin', 'admin', 0);
INSERT INTO gp_authority(
            id, authority, string_id, account_id)
    VALUES (1, 'User', 'user', 1);
INSERT INTO gp_authority(
            id, authority, string_id, account_id)
    VALUES (2, 'Viewer', 'viewer', 2);
