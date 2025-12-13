
insert into users (username, email, password) values
    ('john_doe', 'john.doe@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
    ('jane_smith', 'jane.smith@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
    ('bob_wilson', 'bob.wilson@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy');


insert into categories (name) values
    ('Technology'),
    ('Science'),
    ('Travel'),
    ('Food'),
    ('Lifestyle'),
    ('Sports');


insert into posts (title, text, user_id) values
    ('Introduction to Spring Boot', 'Spring Boot is a powerful framework for building Java applications. It simplifies the development process and provides many out-of-the-box features.', 1),
    ('The Future of AI', 'Artificial Intelligence is rapidly evolving and changing the way we interact with technology. This post explores the latest trends and future possibilities.', 1),
    ('Best Travel Destinations 2024', 'Discover the most beautiful and exciting places to visit this year. From tropical beaches to mountain peaks, there is something for everyone.', 2),
    ('Healthy Cooking Tips', 'Learn how to prepare delicious and nutritious meals at home. These simple tips will help you maintain a healthy lifestyle.', 2),
    ('Morning Routine for Success', 'Starting your day right can make all the difference. Here are some proven strategies to boost your productivity and well-being.', 3);


insert into comments (text, user_id, post_id) values
    ('Great article! Very informative.', 2, 1),
    ('I completely agree with your points about AI.', 3, 2),
    ('Thanks for sharing these travel tips!', 1, 3),
    ('These cooking tips are really helpful.', 3, 4),
    ('I will try this routine tomorrow!', 1, 5),
    ('Excellent post, keep up the good work!', 2, 1);


insert into likes (user_id, post_id) values
    (2, 1),
    (3, 1),
    (1, 2),
    (3, 2),
    (1, 3),
    (3, 3),
    (1, 4),
    (2, 4),
    (2, 5);


insert into post_categories (post_id, category_id) values
    (1, 1),
    (2, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (4, 5),
    (5, 5);

