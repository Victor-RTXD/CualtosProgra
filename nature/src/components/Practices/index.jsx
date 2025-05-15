import React from "react";

const practices = [
  {
    category: "Alimentación Sostenible",
    items: [
      {
        title: "Cultiva tu propio alimento",
        description: "Aprende a crear huertos caseros en espacios reducidos.",
        url: "https://ejemplo.com/huerto-casero",
        image: "src/assets/Screenshot 2025-05-14 114759.png",
      },
      {
        title: "Composta en casa",
        description: "Reduce residuos orgánicos y mejora la salud del suelo.",
        url: "https://ejemplo.com/composta",
        image: "/images/composta.jpg",
      },
    ],
  },
  {
    category: "Reciclaje Creativo",
    items: [
      {
        title: "Macetas con botellas",
        description: "Transforma botellas plásticas en macetas reutilizables.",
        url: "https://ejemplo.com/macetas-botellas",
        image: "/images/macetas.jpg",
      },
      {
        title: "Lámparas con frascos",
        description: "Convierte frascos de vidrio en lámparas decorativas.",
        url: "https://ejemplo.com/lamparas-frascos",
        image: "/images/lamparas.jpg",
      },
    ],
  },
  // Puedes seguir con el resto...
];

function getCategoryColors(category) {
  switch (category) {
    case "Alimentación Sostenible":
      return { bg: "bg-green-800 bg-opacity-40", text: "text-green-300" };
    case "Reciclaje Creativo":
      return { bg: "bg-gray-700 bg-opacity-40", text: "text-gray-300" };
    case "Eficiencia Energética":
      return { bg: "bg-yellow-700 bg-opacity-40", text: "text-yellow-300" };
    case "Consumo Responsable":
      return { bg: "bg-teal-800 bg-opacity-40", text: "text-teal-300" };
    case "Tecnología y Educación":
      return { bg: "bg-blue-800 bg-opacity-40", text: "text-blue-300" };
    default:
      return { bg: "bg-slate-700 bg-opacity-40", text: "text-gray-300" };
  }
}

export default function Practices() {
  return (
    <div className="min-h-screen bg-gradient-to-b from-slate-800 to-slate-900 text-gray-100 px-4 py-12">
      <div className="container mx-auto">
        <h1 className="text-4xl font-bold text-teal-300 text-center mb-12">
          Buenas Prácticas
        </h1>

        {practices.map((group, index) => {
          const { bg, text } = getCategoryColors(group.category);
          return (
            <div key={index} className="mb-12">
              <h2 className={`text-2xl font-bold ${text} mb-6`}>
                {group.category}
              </h2>
              <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
                {group.items.map((item, idx) => (
                  <div key={idx} className={`${bg} rounded-xl shadow-lg overflow-hidden`}>
                    {item.image && (
                      <img
                        src={item.image}
                        alt={item.title}
                        className="w-full h-48 object-cover"
                      />
                    )}
                    <div className="p-6">
                      <h3 className={`text-xl font-bold ${text} mb-3`}>
                        {item.title}
                      </h3>
                      <p className="text-gray-300 mb-4">{item.description}</p>
                      <a
                        href={item.url}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="inline-block bg-teal-600 hover:bg-teal-700 text-white font-bold py-2 px-4 rounded-lg transition"
                      >
                        Ver más
                      </a>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}
