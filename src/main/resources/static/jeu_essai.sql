-- Insertion dans `roles`
INSERT INTO roles (name)
VALUES ('Admin'),
       ('User'),
       ('Botanist');

-- Insertion dans `status` TODO: à revoir
INSERT INTO status (name)
VALUES ('Actif'),
       ('Inactif'),
       ('En attente'),
       ('Confirmé'),
       ('Terminé');

-- Insertion dans `users` TODO: (password à hasher directement grâce à la méthode de hashage de Spring Security)
INSERT INTO users (active, city, street, zip, created_at, email, firstname, lastname, password, phone, updated_at,
                   username, x, y)
VALUES (1, 'Paris', 'Rue de Rivoli', '75001', NOW(), 'user1@example.com', 'Jean', 'Dupont', 'admin', '0123456789',
        NOW(), 'jdupont', 48.8566, 2.3522),
       (1, 'Lyon', 'Rue de la République', '69001', NOW(), 'user2@example.com', 'Marie', 'Curie', 'admin',
        '0234567891', NOW(), 'mcurie', 45.7640, 4.8357),
       (1, 'Marseille', 'La Canebière', '13001', NOW(), 'user3@example.com', 'Pierre', 'Durand', 'admin',
        '0345678912', NOW(), 'pdurand', 43.2965, 5.3698),
       (1, 'Toulouse', 'Allées Jean Jaurès', '31000', NOW(), 'user4@example.com', 'Alice', 'Martin', 'admin',
        '0456789123', NOW(), 'amartin', 43.6045, 1.4440),
       (1, 'Nice', 'Promenade des Anglais', '06000', NOW(), 'user5@example.com', 'Lucas', 'Moreau', 'admin',
        '0567891234', NOW(), 'lmoreau', 43.7102, 7.2620);

-- Insertion fictive dans `photos` (le blob est représenté de manière simplifiée) TODO: Ajouter des photos aux commentaires et aux plantes
INSERT INTO photos (created_at, image_blob, commentaire_id, user_id, plant_id)
VALUES (NOW(), 'blob1', NULL, 1, NULL),
       (NOW(), 'blob2', NULL, 2, NULL),
       (NOW(), 'blob3', NULL, 3, NULL),
       (NOW(), 'blob4', NULL, 4, NULL),
       (NOW(), 'blob5', NULL, 5, NULL);

-- Insertion dans `plants`
INSERT INTO plants (care_instructions, created_at, name, species, updated_at, user_id)
VALUES ('Arroser chaque semaine', NOW(), 'Orchidée', 'Phalaenopsis', NOW(), 1),
       ('Exposition au soleil modérée', NOW(), 'Cactus', 'Cactaceae', NOW(), 2),
       ('Maintenir le sol humide', NOW(), 'Bonsaï', 'Ficus', NOW(), 3),
       ('Arroser deux fois par semaine', NOW(), 'Aloe Vera', 'Aloès', NOW(), 4),
       ('Peu d\'eau nécessaire', NOW(), 'Plante grasse', 'Succulente', NOW(), 5);

-- Insertion dans `guardianships`
INSERT INTO guardianships (description, end_date, start_date, title, guardian_user_id, owner_user_id, plant_id,
                           status_id)
VALUES ('Garde pendant les vacances', NOW(), NOW(), 'Garde Orchidée', 2, 1, 1, 1),
       ('Garde pendant un déménagement', NOW(), NOW(), 'Garde Cactus', 3, 2, 2, 2),
       ('Garde temporaire pour rénovation', NOW(), NOW(), 'Garde Bonsaï', 4, 3, 3, 3),
       ('Garde durant un voyage d\'affaires', NOW(), NOW(), 'Garde Aloe Vera', 5, 4, 4, 4),
       ('Garde pour cause d\'absence prolongée', NOW(), NOW(), 'Garde Plante grasse', 1, 5, 5, 5);

-- Insertion dans `messages`
INSERT INTO messages (content, created_at, updated_at, guardianship_id, receiver_id, sender_id)
VALUES ('Ton orchidée se porte bien', NOW(), NOW(), 1, 1, 2),
       ('Le cactus a besoin de plus de lumière', NOW(), NOW(), 2, 2, 3),
       ('Le bonsaï a été rempoté', NOW(), NOW(), 3, 3, 4),
       ('L\'aloe vera a grandi', NOW(), NOW(), 4, 4, 5),
       ('La plante grasse est florissante', NOW(), NOW(), 5, 5, 1);

-- Insertion dans `tickets`
INSERT INTO tickets (created_at, description, status, title, updated_at, plant_id, user_id)
VALUES (NOW(), 'Problème avec l\'orchidée', 'Ouvert', 'Problème d\'arrosage', NOW(), 1, 1),
       (NOW(), 'Cactus perd ses épines', 'Ouvert', 'Santé du cactus', NOW(), 2, 2),
       (NOW(), 'Bonsaï a des feuilles jaunes', 'Ouvert', 'Entretien du bonsaï', NOW(), 3, 3),
       (NOW(), 'Aloe Vera ne fleurit pas', 'Ouvert', 'Floraison Aloe Vera', NOW(), 4, 4),
       (NOW(), 'Plante grasse malade', 'Ouvert', 'Maladie succulente', NOW(), 5, 5);

-- Insertion dans `ticket_comments`
INSERT INTO ticket_comments (comment, created_at, ticket_id, user_id)
VALUES ('Essayez d\'arroser moins fréquemment.', NOW(), 1, 2),
       ('Le cactus a peut-être besoin de plus de soleil.', NOW(), 2, 3),
       ('Les feuilles jaunes sont souvent un signe de sur-arrosage.', NOW(), 3, 4),
       ('L\'Aloe Vera a besoin de plus de lumière pour fleurir.', NOW(), 4, 5),
       ('Les maladies des plantes grasses sont souvent liées à l\'excès d\'eau.', NOW(), 5, 1);

-- Insertion dans `user_roles`
INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 1),
       (5, 2);
