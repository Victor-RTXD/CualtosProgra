import { useState } from "react";
import { Globe, ExternalLink } from "lucide-react";

// Datos de los objetivos con colores específicos para cada ODS
const objectives = [
  {
    id: 1,
    title: "Fin de la pobreza",
    description: "Erradicar la pobreza en todas sus formas en todo el mundo.",
    link: "https://www.un.org/sustainabledevelopment/poverty/",
    color: "from-red-800 to-red-700",
    hoverColor: "bg-red-700",
    iconColor: "text-red-400",
    borderColor: "border-red-500"
  },
  {
    id: 2,
    title: "Hambre cero",
    description: "Poner fin al hambre, lograr la seguridad alimentaria y promover la agricultura sostenible.",
    link: "https://www.un.org/sustainabledevelopment/hunger/",
    color: "from-amber-800 to-amber-700",
    hoverColor: "bg-amber-700",
    iconColor: "text-amber-400",
    borderColor: "border-amber-500"
  },
  {
    id: 3, 
    title: "Salud y bienestar",
    description: "Garantizar una vida sana y promover el bienestar para todos en todas las edades.",
    link: "https://www.un.org/sustainabledevelopment/health/",
    color: "from-green-800 to-green-700",
    hoverColor: "bg-green-700",
    iconColor: "text-green-400",
    borderColor: "border-green-500"
  },
  {
    id: 4,
    title: "Educación de calidad",
    description: "La educación es la base para mejorar nuestra vida y el desarrollo sostenible.",
    link: "https://www.un.org/sustainabledevelopment/education/",
    color: "from-rose-800 to-rose-700",
    hoverColor: "bg-rose-700",
    iconColor: "text-rose-400",
    borderColor: "border-rose-500"
  },
  {
    id: 5,
    title: "Igualdad de género",
    description: "La igualdad entre los géneros es fundamental para conseguir un mundo pacífico, próspero y sostenible.",
    link: "https://www.un.org/sustainabledevelopment/gender-equality/",
    color: "from-orange-800 to-orange-700",
    hoverColor: "bg-orange-700",
    iconColor: "text-orange-400",
    borderColor: "border-orange-500"
  },
  {
    id: 6,
    title: "Agua limpia y saneamiento",
    description: "El agua libre de impurezas y accesible para todos es parte esencial del mundo en que queremos vivir.",
    link: "https://www.un.org/sustainabledevelopment/water-and-sanitation/",
    color: "from-blue-800 to-blue-700",
    hoverColor: "bg-blue-700",
    iconColor: "text-blue-400",
    borderColor: "border-blue-500"
  },
  {
    id: 7,
    title: "Energía asequible y no contaminante",
    description: "El acceso a una energía asequible, segura, sostenible y moderna para todos.",
    link: "https://www.un.org/sustainabledevelopment/energy/",
    color: "from-yellow-800 to-yellow-700",
    hoverColor: "bg-yellow-700",
    iconColor: "text-yellow-400",
    borderColor: "border-yellow-500"
  },
  {
    id: 8,
    title: "Trabajo decente y crecimiento económico",
    description: "Promover el crecimiento económico inclusivo y sostenible, el empleo y el trabajo decente para todos.",
    link: "https://www.un.org/sustainabledevelopment/economic-growth/",
    color: "from-purple-800 to-purple-700",
    hoverColor: "bg-purple-700",
    iconColor: "text-purple-400",
    borderColor: "border-purple-500"
  },
  {
    id: 9,
    title: "Industria, innovación e infraestructuras",
    description: "Construir infraestructuras resilientes, promover la industrialización sostenible y fomentar la innovación.",
    link: "https://www.un.org/sustainabledevelopment/infrastructure-industrialization/",
    color: "from-indigo-800 to-indigo-700",
    hoverColor: "bg-indigo-700",
    iconColor: "text-indigo-400",
    borderColor: "border-indigo-500"
  },
  {
    id: 10,
    title: "Reducción de las desigualdades",
    description: "Reducir la desigualdad en y entre los países para un desarrollo social y económico sostenible.",
    link: "https://www.un.org/sustainabledevelopment/inequality/",
    color: "from-pink-800 to-pink-700",
    hoverColor: "bg-pink-700",
    iconColor: "text-pink-400",
    borderColor: "border-pink-500"
  },
  {
    id: 11,
    title: "Ciudades y comunidades sostenibles",
    description: "Lograr que las ciudades sean más inclusivas, seguras, resilientes y sostenibles.",
    link: "https://www.un.org/sustainabledevelopment/cities/",
    color: "from-amber-800 to-amber-700",
    hoverColor: "bg-amber-700",
    iconColor: "text-amber-400",
    borderColor: "border-amber-500"
  },
  {
    id: 12,
    title: "Producción y consumo responsables",
    description: "Garantizar modalidades de consumo y producción sostenibles para el futuro del planeta.",
    link: "https://www.un.org/sustainabledevelopment/sustainable-consumption-production/",
    color: "from-emerald-800 to-emerald-700",
    hoverColor: "bg-emerald-700",
    iconColor: "text-emerald-400",
    borderColor: "border-emerald-500"
  },
  {
    id: 13,
    title: "Acción por el clima",
    description: "Adoptar medidas urgentes para combatir el cambio climático y sus efectos.",
    link: "https://www.un.org/sustainabledevelopment/climate-change/",
    color: "from-teal-800 to-teal-700",
    hoverColor: "bg-teal-700",
    iconColor: "text-teal-400",
    borderColor: "border-teal-500"
  },
  {
    id: 14,
    title: "Vida submarina",
    description: "Conservar y utilizar de forma sostenible los océanos, los mares y los recursos marinos.",
    link: "https://www.un.org/sustainabledevelopment/oceans/",
    color: "from-cyan-800 to-cyan-700",
    hoverColor: "bg-cyan-700",
    iconColor: "text-cyan-400",
    borderColor: "border-cyan-500"
  },
  {
    id: 15,
    title: "Vida de ecosistemas terrestres",
    description: "Gestionar sosteniblemente los bosques, luchar contra la desertificación y detener la pérdida de biodiversidad.",
    link: "https://www.un.org/sustainabledevelopment/biodiversity/",
    color: "from-lime-800 to-lime-700",
    hoverColor: "bg-lime-700",
    iconColor: "text-lime-400",
    borderColor: "border-lime-500"
  },
  {
    id: 16,
    title: "Paz, justicia e instituciones sólidas",
    description: "Promover sociedades justas, pacíficas e inclusivas para el desarrollo sostenible.",
    link: "https://www.un.org/sustainabledevelopment/peace-justice/",
    color: "from-blue-800 to-blue-700",
    hoverColor: "bg-blue-700",
    iconColor: "text-blue-400",
    borderColor: "border-blue-500"
  },
  {
    id: 17,
    title: "Alianzas para lograr los objetivos",
    description: "Revitalizar la Alianza Mundial para el Desarrollo Sostenible.",
    link: "https://www.un.org/sustainabledevelopment/globalpartnerships/",
    color: "from-violet-800 to-violet-700",
    hoverColor: "bg-violet-700",
    iconColor: "text-violet-400",
    borderColor: "border-violet-500"
  },
];

// Estilos CSS personalizados para el efecto de volteo
const flipCardStyles = `
  .flip-card-container {
    perspective: 1000px;
  }
  
  .flip-card-inner {
    position: relative;
    width: 100%;
    height: 100%;
    transition: transform 0.6s;
    transform-style: preserve-3d;
  }
  
  .flip-card-container:hover .flip-card-inner,
  .flip-card-container.flipped .flip-card-inner {
    transform: rotateY(180deg);
  }
  
  .flip-card-front,
  .flip-card-back {
    position: absolute;
    width: 100%;
    height: 100%;
    -webkit-backface-visibility: hidden;
    backface-visibility: hidden;
    border-radius: 0.75rem;
  }
  
  .flip-card-back {
    transform: rotateY(180deg);
  }
`;

const Onu = () => {
  return (
    <div className="min-h-screen bg-gradient-to-b from-slate-800 to-slate-900 text-gray-100 py-12 px-4">
      {/* Estilos personalizados */}
      <style>{flipCardStyles}</style>
      
      <div className="container mx-auto">
        {/* Header Section */}
        <div className="text-center mb-12">
          <div className="flex justify-center mb-4">
            <Globe className="h-16 w-16 text-teal-400" />
          </div>
          <h1 className="text-4xl font-bold text-teal-300 mb-4">Objetivos de Desarrollo Sostenible</h1>
          <p className="text-xl text-gray-300 max-w-3xl mx-auto">
            Los 17 Objetivos de Desarrollo Sostenible de la ONU son nuestro plan para crear un mundo mejor y más sostenible para todos.
          </p>
        </div>

        {/* Filter Section */}
        <div className="mb-10">
          <div className="flex flex-wrap justify-center gap-2">
            <button className="bg-teal-700 hover:bg-teal-600 text-white px-4 py-2 rounded-lg transition">
              Todos los ODS
            </button>
            <button className="bg-slate-700 hover:bg-slate-600 text-white px-4 py-2 rounded-lg transition">
              Medioambientales
            </button>
            <button className="bg-slate-700 hover:bg-slate-600 text-white px-4 py-2 rounded-lg transition">
              Sociales
            </button>
            <button className="bg-slate-700 hover:bg-slate-600 text-white px-4 py-2 rounded-lg transition">
              Económicos
            </button>
          </div>
        </div>

        {/* Cards Grid */}
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {objectives.map((obj) => (
            <FlippingCard key={obj.id} objective={obj} />
          ))}
        </div>

        {/* Bottom CTA */}
        <div className="mt-16 text-center">
          <h2 className="text-2xl font-bold text-teal-300 mb-4">¿Cómo puedes contribuir?</h2>
          <p className="text-gray-300 max-w-2xl mx-auto mb-8">
            Cada acción cuenta. Descubre cómo puedes participar activamente en la consecución de estos objetivos desde tu comunidad.
          </p>
          <a 
            href="https://www.un.org/sustainabledevelopment/es/take-action/" 
            target="_blank" 
            rel="noopener noreferrer"
            className="inline-flex items-center bg-teal-600 hover:bg-teal-700 text-white font-medium py-3 px-6 rounded-lg transition"
          >
            Tomar acción
            <ExternalLink className="ml-2 h-4 w-4" />
          </a>
        </div>
      </div>
    </div>
  );
};

const FlippingCard = ({ objective }) => {
  const [flipped, setFlipped] = useState(false);

  return (
    <div
      className={`h-72 flip-card-container cursor-pointer ${flipped ? "flipped" : ""}`}
      onClick={() => setFlipped(!flipped)}
      onMouseEnter={() => setFlipped(true)}
      onMouseLeave={() => setFlipped(false)}
    >
      <div className="flip-card-inner h-full w-full shadow-xl">
        {/* Front */}
        <div className={`flip-card-front p-6 bg-gradient-to-br ${objective.color} border-l-4 ${objective.borderColor}`}>
          <div className="flex items-center justify-between mb-4">
            <h3 className="text-lg font-bold mb-3">Objetivo {objective.id}</h3>
            <div className="text-xs font-medium px-2 py-1 rounded bg-slate-800 bg-opacity-30 text-gray-200">
              ODS {objective.id}
            </div>
          </div>
          <h2 className="text-xl font-bold text-white mb-3">{objective.title}</h2>
          <p className="text-gray-200 text-sm">{objective.description}</p>
          
          <div className="absolute bottom-4 right-4">
            <span className="text-xs text-gray-300 opacity-70 flex items-center">
              Clic para más info
              <svg className="w-3 h-3 ml-1" fill="currentColor" viewBox="0 0 20 20">
                <path fillRule="evenodd" d="M15.707 4.293a1 1 0 010 1.414l-5 5a1 1 0 01-1.414 0l-5-5a1 1 0 011.414-1.414L10 8.586l4.293-4.293a1 1 0 011.414 0z" clipRule="evenodd" />
              </svg>
            </span>
          </div>
        </div>

        {/* Back */}
        <div className={`flip-card-back p-6 ${objective.hoverColor} text-white`}>
          <div className="flex flex-col h-full justify-between">
            <div>
              <h3 className="text-lg font-bold mb-3">Objetivo {objective.id}</h3>
              <p className="text-sm mb-4">
                Este objetivo busca crear un impacto positivo en nuestra sociedad y planeta a través de acciones concretas y medibles.
              </p>
            </div>
            
            <div className="space-y-3">
              <a 
                href={objective.link} 
                target="_blank" 
                rel="noopener noreferrer" 
                className="inline-flex items-center bg-white text-slate-800 hover:bg-gray-200 px-4 py-2 rounded-lg text-sm font-medium transition w-full justify-center"
                onClick={(e) => e.stopPropagation()}
              >
                Sitio oficial
                <ExternalLink className="ml-2 h-4 w-4" />
              </a>
              
              <button 
                className="inline-flex items-center justify-center w-full bg-black bg-opacity-30 hover:bg-opacity-40 text-white px-4 py-2 rounded-lg text-sm font-medium transition"
                onClick={(e) => e.stopPropagation()}
              >
                Ver recursos
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Onu;