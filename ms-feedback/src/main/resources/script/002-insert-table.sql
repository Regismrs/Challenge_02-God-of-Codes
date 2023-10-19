USE db_ecommerce;
TRUNCATE TABLE feedback_tb;
INSERT INTO feedback_tb (order_id, scale, comment) VALUES
  (1, 'VERY_DISSATISFIED', 'Muito insatisfeito com o serviço.'),
  (2, 'DISSATISFIED', 'Insatisfeito com o tempo de entrega.'),
  (3, 'NEUTRAL', 'Nenhuma reclamação nem elogio.'),
  (4, 'SATISFIED', 'Satisfeito com a qualidade do produto.'),
  (5, 'VERY_SATISFIED', 'Muito satisfeito com o atendimento.'),
  (6, 'VERY_DISSATISFIED', 'Terrível experiência com a empresa.'),
  (7, 'DISSATISFIED', 'O pedido chegou com atraso.'),
  (8, 'NEUTRAL', 'Acho que poderiam melhorar o serviço.'),
  (9, 'SATISFIED', 'Gostei do produto que comprei.'),
  (10, 'VERY_SATISFIED', 'Atendimento excepcional e rápido.');