import { useState } from "react";
import { motion } from "framer-motion";

const objectives = [
  {
    id: 1,
    title: "Fin de la pobreza",
    description: "Erradicar la pobreza en todas sus formas en todo el mundo.",
    link: "https://www.un.org/sustainabledevelopment/poverty/"
  },
  {
    id: 2,
    title: "Hambre cero",
    description: "Poner fin al hambre, lograr la seguridad alimentaria.",
    link: "https://www.un.org/sustainabledevelopment/hunger/"
  },
  {
    id: 3,
    title: "Salud y bienestar",
    description: "Garantizar una vida sana y promover el bienestar.",
    link: "https://www.un.org/sustainabledevelopment/health/"
  },
  {
    id: 4,
    title: "Educación de calidad",
    description: "La educación es la base para mejorar nuestra vida y el desarrollo sostenible.",
    link: "https://www.un.org/sustainabledevelopment/health/"
  },
  {
    id: 5,
    title: "Igualdad de género",
    description: "La igualdad entre los géneros no es solo un derecho humano fundamental, sino la base necesaria para conseguir un mundo pacífico, próspero y sostenible.",
    link: "https://www.un.org/sustainabledevelopment/health/"
  },
  {
    id: 6,
    title: "Agua limpia y saneamiento",
    description: "El agua libre de impurezas y accesible para todos es parte esencial del mundo en que queremos vivir.",
    link: "https://www.un.org/sustainabledevelopment/health/"
  },
  {
    id: 7,
    title: "Energía asequible y no contaminante",
    description: "Garantizar una vida sana y promover el bienestar.",
    link: "https://www.un.org/sustainabledevelopment/health/"
  },
  {
    id: 8,
    title: "Trabajo decente y crecimiento económico",
    description: "Garantizar una vida sana y promover el bienestar.",
    link: "https://www.un.org/sustainabledevelopment/health/"
  },
  {
    id: 9,
    title: "Industria, innovación e infraestructuras",
    description: "Garantizar una vida sana y promover el bienestar.",
    link: "https://www.un.org/sustainabledevelopment/health/"
  },
  {
    id: 10,
    title: "Reducción de las desigualdades",
    description: "Garantizar una vida sana y promover el bienestar.",
    link: "https://www.un.org/sustainabledevelopment/health/"
  },
  {
    id: 11,
    title: "Ciudades y comunidades sostenibles",
    description: "Garantizar una vida sana y promover el bienestar.",
    link: "https://www.un.org/sustainabledevelopment/health/"
  },
  {
    id: 12,
    title: "Producción y consumo responsables",
    description: "Garantizar una vida sana y promover el bienestar.",
    link: "https://www.un.org/sustainabledevelopment/health/"
  },
  {
    id: 13,
    title: "Producción y consumo responsables",
    description: "Garantizar una vida sana y promover el bienestar.",
    link: "https://www.un.org/sustainabledevelopment/health/"
  },
  {
    id: 14,
    title: "Vida submarina",
    description: "Garantizar una vida sana y promover el bienestar.",
    link: "https://www.un.org/sustainabledevelopment/health/"
  },
  {
    id: 15,
    title: "Vida de ecosistemas terrestres",
    description: "Garantizar una vida sana y promover el bienestar.",
    link: "https://www.un.org/sustainabledevelopment/health/"
  },
  {
    id: 16,
    title: "Paz, justicia e instituciones sólidas",
    description: "Garantizar una vida sana y promover el bienestar.",
    link: "https://www.un.org/sustainabledevelopment/health/"
  },
  {
    id: 17,
    title: "Alianzas para lograr los objetivos",
    description: "Garantizar una vida sana y promover el bienestar.",
    link: "https://www.un.org/sustainabledevelopment/health/"
  },
];

const Onu = () => {
  return (
    <div className="min-h-screen bg-[#242424] text-white p-10">
      <h1 className="text-3xl font-bold text-center mb-10">Objetivos de la ONU</h1>
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
        {objectives.map((obj) => (
          <FlippingCard key={obj.id} objective={obj} />
        ))}
      </div>
    </div>
  );
};

const FlippingCard = ({ objective }) => {
  const [flipped, setFlipped] = useState(false);

  return (
    <div
      className="relative w-full h-60 perspective"
      onMouseEnter={() => setFlipped(true)}
      onMouseLeave={() => setFlipped(false)}
    >
      <motion.div
        className="w-full h-full relative"
        animate={{ rotateY: flipped ? 180 : 0 }}
        transition={{ duration: 0.6 }}
        style={{ transformStyle: "preserve-3d" }}
      >
        {/* Front */}
        <div className="absolute w-full h-full bg-gray-800 text-white flex flex-col items-center justify-center rounded-2xl shadow-lg" style={{ backfaceVisibility: "hidden" }}>
          <h2 className="text-xl font-bold text-center">{objective.title}</h2>
          <p className="text-sm text-center px-4 mt-2">{objective.description}</p>
        </div>

        {/* Back */}
        <div className="absolute w-full h-full bg-blue-600 text-white flex flex-col items-center justify-center rounded-2xl shadow-lg" style={{ backfaceVisibility: "hidden", transform: "rotateY(180deg)" }}>
          <p className="text-sm text-center px-4">Más información sobre este objetivo</p>
          <a href={objective.link} target="_blank" rel="noopener noreferrer" className="mt-4 bg-white text-blue-600 px-4 py-2 rounded-lg font-bold hover:bg-gray-300">Leer más</a>
        </div>
      </motion.div>
    </div>
  );
};

export default Onu;
