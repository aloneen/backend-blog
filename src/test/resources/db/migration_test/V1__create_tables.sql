
drop table if exists post_categories cascade;
drop table if exists likes cascade;
drop table if exists comments cascade;
drop table if exists posts cascade;
drop table if exists users_permissions cascade;
drop table if exists permissions cascade;
drop table if exists categories cascade;
drop table if exists users cascade;


create table users (
    id bigserial primary key,
    username varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null
);


create table categories (
    id bigserial primary key,
    name varchar(255) not null
);


create table posts (
    id bigserial primary key,
    title varchar(255) not null,
    text text,
    user_id bigint not null,
    constraint fk_post_user foreign key (user_id) references users(id) on delete cascade
);


create table comments (
    id bigserial primary key,
    text text not null,
    user_id bigint not null,
    post_id bigint not null,
    constraint fk_comment_user foreign key (user_id) references users(id) on delete cascade,
    constraint fk_comment_post foreign key (post_id) references posts(id) on delete cascade
);


create table likes (
    id bigserial primary key,
    user_id bigint not null,
    post_id bigint not null,
    constraint fk_like_user foreign key (user_id) references users(id) on delete cascade,
    constraint fk_like_post foreign key (post_id) references posts(id) on delete cascade,
    constraint uk_like_user_post unique (user_id, post_id)
);


create table post_categories (
    post_id bigint not null,
    category_id bigint not null,
    constraint fk_post_category_post foreign key (post_id) references posts(id) on delete cascade,
    constraint fk_post_category_category foreign key (category_id) references categories(id) on delete cascade,
    constraint pk_post_categories primary key (post_id, category_id)
);