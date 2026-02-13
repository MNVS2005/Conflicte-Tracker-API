-- =========================
-- 🌍 COUNTRIES
-- =========================
INSERT INTO country (id, name, code) VALUES
(1, 'Ucrania', 'UA'),
(2, 'Rusia', 'RU'),
(3, 'Estados Unidos', 'US'),
(4, 'Reino Unido', 'GB'),
(5, 'Francia', 'FR'),
(6, 'Alemania', 'DE'),
(7, 'Israel', 'IL'),
(8, 'Palestina', 'PS');



-- =========================
-- ⚔️ CONFLICTS
-- =========================
INSERT INTO conflict (id, name, start_date, status, description) VALUES
(1, 'Guerra Rusia-Ucrania', '2022-02-24', 'ACTIVE',
 'Conflicto armado a gran escala tras la invasión rusa de Ucrania en 2022.'),

(2, 'Conflicto Israel-Palestina', '2023-10-07', 'ACTIVE',
 'Escalada del conflicto histórico entre Israel y grupos armados palestinos.'),

(3, 'Guerra Civil Siria', '2011-03-15', 'FROZEN',
 'Conflicto interno con múltiples actores locales e internacionales.');



-- =========================
-- 🔗 CONFLICT - COUNTRY (ManyToMany)
-- =========================
INSERT INTO conflict_country (conflict_id, country_id) VALUES
-- Rusia - Ucrania
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),

-- Israel - Palestina
(2, 7),
(2, 8),
(2, 3),

-- Siria
(3, 2),
(3, 3);



-- =========================
-- 🧨 EVENTS
-- =========================
INSERT INTO event (id, event_date, location, description, conflict_id) VALUES
(1, '2022-02-24', 'Kiev',
 'Inicio de la invasión rusa a gran escala en territorio ucraniano.', 1),

(2, '2022-04-02', 'Bucha',
 'Retirada rusa de Bucha y descubrimiento de presuntas atrocidades.', 1),

(3, '2023-10-07', 'Sur de Israel',
 'Ataque coordinado de militantes palestinos contra territorio israelí.', 2),

(4, '2023-10-10', 'Gaza',
 'Bombardeos intensivos en la Franja de Gaza tras la escalada del conflicto.', 2),

(5, '2011-03-15', 'Damasco',
 'Inicio de protestas que escalaron hacia la guerra civil en Siria.', 3);



-- =========================
-- 🪖 FACTIONS
-- (Asumiendo estructura: faction(id, name, description, conflict_id))
-- =========================
INSERT INTO faction (id, name, description, conflict_id) VALUES
(1, 'Fuerzas Armadas de Ucrania', 'Ejército regular de Ucrania.', 1),
(2, 'Fuerzas Armadas Rusas', 'Ejército de la Federación Rusa.', 1),
(3, 'Fuerzas de Defensa de Israel', 'Ejército del Estado de Israel.', 2),
(4, 'Hamás', 'Organización político-militar palestina.', 2),
(5, 'Gobierno Sirio', 'Fuerzas leales al gobierno de Bashar al-Ásad.', 3);



-- =========================
-- 🤝 FACTION - SUPPORTED COUNTRY (ManyToMany)
-- (Asumiendo tabla: faction_supported_country)
-- =========================
INSERT INTO faction_supported_country (faction_id, country_id) VALUES
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(2, 2),
(3, 7),
(4, 8),
(5, 2);
