USE auth_service;

-- =============================================
-- STEP 1: Clean existing data (safe reset)
-- =============================================
DELETE FROM roles_permissions;
DELETE FROM user;
DELETE FROM permission;
DELETE FROM role;

-- =============================================
-- STEP 2: Create Roles
-- =============================================
INSERT INTO role (role_id, description, is_deleted) VALUES
('role-admin',         'ADMIN',         0),
('role-officer',       'OFFICER',       0),
('role-investigator',  'INVESTIGATOR',  0),
('role-forensic',      'FORENSIC',      0),
('role-prosecutor',    'PROSECUTOR',    0),
('role-viewer',        'VIEWER',        0);

-- =============================================
-- STEP 3: Create Permissions (one per role)
-- =============================================
INSERT INTO permission (permission_id, description, is_deleted) VALUES
('perm-all',           'ALL',           0),
('perm-officer',       'REPORT_READ,REPORT_CREATE,REPORT_ACCEPT,REPORT_REJECT,REPORT_DELETE,VIEW_EVIDENCE,VIEW_RECORD_INFO', 0),
('perm-investigator',  'REPORT_READ,VIEW_EVIDENCE,ADD_EVIDENCE,EDIT_EVIDENCE,VIEW_RECORD_INFO,ADD_RECORD_INFO,EDIT_RECORD_INFO,VIEW_MEASURE_SURVEY,ADD_MEASURE_SURVEY,EDIT_MEASURE_SURVEY', 0),
('perm-forensic',      'VIEW_EVIDENCE,ADD_PHYSICAL_RESULT,VIEW_PHYSICAL_RESULT,EDIT_PHYSICAL_RESULT,ADD_FORENSIC_RESULT,VIEW_FORENSIC_RESULT,EDIT_FORENSIC_RESULT,ADD_DIGITAL_RESULT,VIEW_DIGITAL_RESULT,EDIT_DIGITAL_RESULT,ADD_FINANCIAL_RESULT,VIEW_FINANCIAL_RESULT,EDIT_FINANCIAL_RESULT', 0),
('perm-prosecutor',    'REPORT_READ,VIEW_EVIDENCE,VIEW_RECORD_INFO,ASSIGN_SUSPECT,ASSIGN_CASE,ASSIGN_WARRANT', 0),
('perm-viewer',        'REPORT_READ,VIEW_EVIDENCE,VIEW_RECORD_INFO,VIEW_PHYSICAL_RESULT,VIEW_FORENSIC_RESULT,VIEW_DIGITAL_RESULT,VIEW_FINANCIAL_RESULT,VIEW_MEASURE_SURVEY', 0);

-- =============================================
-- STEP 4: Link Roles to Permissions
-- =============================================
INSERT INTO roles_permissions (role_id, permission_id) VALUES
('role-admin',         'perm-all'),
('role-officer',       'perm-officer'),
('role-investigator',  'perm-investigator'),
('role-forensic',      'perm-forensic'),
('role-prosecutor',    'perm-prosecutor'),
('role-viewer',        'perm-viewer');

-- =============================================
-- STEP 5: Create User Accounts
-- BCrypt hash for all passwords = "Test@1234" with strength 10
-- Hash: $2a$10$1lSivvy/C5Pt1v7l3LrjKumW/JYOvosNmIv4Bs/JhwB36FYB7Y1SS
-- (Admin keeps Admin@123)
-- =============================================
INSERT INTO user (user_name, password_hash, full_name, email, phone_number, is_delete, role_id, create_at) VALUES
('admin',        '$2a$10$1lSivvy/C5Pt1v7l3LrjKumW/JYOvosNmIv4Bs/JhwB36FYB7Y1SS', 'System Administrator',   'admin@pdsystem.com',        '555-0001', 0, 'role-admin',        NOW()),
('officer01',    '$2a$10$1lSivvy/C5Pt1v7l3LrjKumW/JYOvosNmIv4Bs/JhwB36FYB7Y1SS', 'James Wilson',            'james.wilson@pd.gov',       '555-0002', 0, 'role-officer',      NOW()),
('detective01',  '$2a$10$1lSivvy/C5Pt1v7l3LrjKumW/JYOvosNmIv4Bs/JhwB36FYB7Y1SS', 'Sarah Connor',            'sarah.connor@pd.gov',       '555-0003', 0, 'role-investigator', NOW()),
('forensic01',   '$2a$10$1lSivvy/C5Pt1v7l3LrjKumW/JYOvosNmIv4Bs/JhwB36FYB7Y1SS', 'Dr. Emily Chen',          'emily.chen@pd.gov',         '555-0004', 0, 'role-forensic',     NOW()),
('prosecutor01', '$2a$10$1lSivvy/C5Pt1v7l3LrjKumW/JYOvosNmIv4Bs/JhwB36FYB7Y1SS', 'Michael Rodriguez',       'michael.rodriguez@pd.gov',  '555-0005', 0, 'role-prosecutor',   NOW()),
('viewer01',     '$2a$10$1lSivvy/C5Pt1v7l3LrjKumW/JYOvosNmIv4Bs/JhwB36FYB7Y1SS', 'David Park',              'david.park@pd.gov',         '555-0006', 0, 'role-viewer',       NOW());

-- =============================================
-- VERIFY
-- =============================================
SELECT u.user_name, u.full_name, r.description AS role_name, p.description AS permissions
FROM user u
JOIN role r ON u.role_id = r.role_id
JOIN roles_permissions rp ON r.role_id = rp.role_id
JOIN permission p ON rp.permission_id = p.permission_id
ORDER BY r.description;
