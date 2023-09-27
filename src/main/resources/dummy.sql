-- Insert data for authors
INSERT INTO
    authors (id, first_name, last_name, biography, publisher)
VALUES
    (
        UNHEX('123e4567e89b12d3a456556642440000'),
        'John',
        'Smith',
        'John Smith is a prolific author with many bestsellers to his name.',
        'XYZ Publishers'
    ),
    (
        UNHEX('223e4567e89b12d3a456556642440000'),
        'Alice',
        'Johnson',
        'Alice Johnson is known for her award-winning novels and essays.',
        'ABC Books'
    ),
    (
        UNHEX('323e4567e89b12d3a456556642440000'),
        'Michael',
        'Brown',
        'Michael Brown specializes in science fiction and fantasy literature.',
        'Sci-Fi Press'
    ),
    (
        UNHEX('423e4567e89b12d3a456556642440000'),
        'Emily',
        'Davis',
        'Emily Davis is a renowned poet and has won several poetry awards.',
        'Poetry House'
    ),
    (
        UNHEX('523e4567e89b12d3a456556642440000'),
        'Robert',
        'Jones',
        'Robert Jones is a historian and author of several historical books.',
        'History Publishers'
    );

-- Insert data for books
INSERT INTO
    books (
        id,
        isbn,
        name,
        description,
        price,
        author_id,
        genre,
        publisher,
        year_published,
        copies_sold
    )
VALUES
    (
        UNHEX('d1a67ec0f2654f4883b6e3be8bcac5dd'),
        '978-1234567890',
        'The Enchanted Forest',
        'A magical adventure through an enchanted forest.',
        19.99,
        UNHEX('123e4567e89b12d3a456556642440000'),
        'Fantasy',
        'Magical Books',
        2021,
        1000
    ),
    (
        UNHEX('d06a5f2010544a1e8fbaf1f8288d86ab'),
        '978-2345678901',
        'Mystery Mansion',
        'Solve the mysteries of the haunted mansion.',
        24.99,
        UNHEX('223e4567e89b12d3a456556642440000'),
        'Mystery',
        'Puzzle Publishing',
        2022,
        800
    ),
    (
        UNHEX('5a747d6f76e140d1bb2b2e8c7b9c2dbd'),
        '978-3456789012',
        'The Galactic Odyssey',
        'Journey through space and time in this epic sci-fi.',
        14.99,
        UNHEX('323e4567e89b12d3a456556642440000'),
        'Science Fiction',
        'Cosmic Books',
        2023,
        1200
    ),
    (
        UNHEX('f85e5771e0c74aa3a7d82c43b30318f5'),
        '978-4567890123',
        'The Dragon\'s Lair',
        'Face the fire-breathing dragon in this thrilling fantasy.',
        29.99,
        UNHEX('423e4567e89b12d3a456556642440000'),
        'Fantasy',
        'Dragon Publishing',
        2020,
        1500
    ),
    (
        UNHEX('ac64c2ed33684c07ac8d700438a99e91'),
        '978-5678901234',
        'Love in Paris',
        'A heartwarming romance set in the city of love.',
        17.99,
        UNHEX('523e4567e89b12d3a456556642440000'),
        'Romance',
        'Romantic Novels',
        2021,
        900
    ),
    (
        UNHEX('5fcd2ed0eab4451a9a5d4eaef5025b59'),
        '978-6789012345',
        'The Dark Conspiracy',
        'Uncover a dark conspiracy in this gripping thriller.',
        22.99,
        UNHEX('123e4567e89b12d3a456556642440000'),
        'Thriller',
        'Secret Books',
        2022,
        1100
    ),
    (
        UNHEX('15a6508389c0454eaf8108c2b2d2a6f4'),
        '978-7890123456',
        'The Scientific Revolution',
        'Explore the scientific discoveries that shaped our world.',
        19.99,
        UNHEX('223e4567e89b12d3a456556642440000'),
        'Non-Fiction',
        'Discovery Publications',
        2023,
        700
    ),
    (
        UNHEX('cdd9785b60d542369da0e43f0f69c8c5'),
        '978-8901234567',
        'The Time Traveler',
        'Join the time traveler on a journey through history.',
        21.99,
        UNHEX('323e4567e89b12d3a456556642440000'),
        'Science Fiction',
        'Chrono Books',
        2020,
        1400
    ),
    (
        UNHEX('aad0a2eaa9d54f0f89adff29b7541dfc'),
        '978-9012345678',
        'Remarkable Lives',
        'Biographies of remarkable individuals.',
        15.99,
        UNHEX('423e4567e89b12d3a456556642440000'),
        'Biography',
        'Biography Press',
        2021,
        950
    );