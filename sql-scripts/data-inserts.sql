INSERT INTO users (nickname, user_role) VALUES('YOUR NAME', 'ADMIN');
INSERT INTO user_api_identity (api_type, api_user_id, user_id) 
VALUES('GOOGLE', 'YOUR GOOGLE ID', (SELECT user_id FROM users WHERE nickname = 'YOUR NAME'));