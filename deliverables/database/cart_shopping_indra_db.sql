PGDMP  "    0                 }            cart_shopping_indra_db    16.2    16.2 $    -           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            .           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            /           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            0           1262    41234    cart_shopping_indra_db    DATABASE     x   CREATE DATABASE cart_shopping_indra_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';
 &   DROP DATABASE cart_shopping_indra_db;
                postgres    false            1           0    0    DATABASE cart_shopping_indra_db    COMMENT     Q   COMMENT ON DATABASE cart_shopping_indra_db IS 'BD carrito de compras de Indra.';
                   postgres    false    3632            �            1259    41243    carrito    TABLE     [   CREATE TABLE public.carrito (
    id bigint NOT NULL,
    total numeric(38,2) DEFAULT 0
);
    DROP TABLE public.carrito;
       public         heap    postgres    false            �            1259    41242    carrito_id_seq    SEQUENCE     �   CREATE SEQUENCE public.carrito_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.carrito_id_seq;
       public          postgres    false    218            2           0    0    carrito_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.carrito_id_seq OWNED BY public.carrito.id;
          public          postgres    false    217            �            1259    41251    carrito_producto    TABLE     �   CREATE TABLE public.carrito_producto (
    id bigint NOT NULL,
    carrito_id bigint,
    producto_id bigint,
    cantidad integer NOT NULL,
    subtotal numeric(38,2) NOT NULL
);
 $   DROP TABLE public.carrito_producto;
       public         heap    postgres    false            �            1259    41250    carrito_producto_id_seq    SEQUENCE     �   CREATE SEQUENCE public.carrito_producto_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.carrito_producto_id_seq;
       public          postgres    false    220            3           0    0    carrito_producto_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.carrito_producto_id_seq OWNED BY public.carrito_producto.id;
          public          postgres    false    219            �            1259    41268    cupon    TABLE     �   CREATE TABLE public.cupon (
    id bigint NOT NULL,
    codigo character varying(255) NOT NULL,
    descuento numeric(38,2) NOT NULL,
    activo boolean DEFAULT true
);
    DROP TABLE public.cupon;
       public         heap    postgres    false            �            1259    41267    cupon_id_seq    SEQUENCE     �   CREATE SEQUENCE public.cupon_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.cupon_id_seq;
       public          postgres    false    222            4           0    0    cupon_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.cupon_id_seq OWNED BY public.cupon.id;
          public          postgres    false    221            �            1259    41236    producto    TABLE     �   CREATE TABLE public.producto (
    id bigint NOT NULL,
    nombre character varying(255) NOT NULL,
    precio numeric(38,2) NOT NULL
);
    DROP TABLE public.producto;
       public         heap    postgres    false            �            1259    41235    producto_id_seq    SEQUENCE     �   CREATE SEQUENCE public.producto_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.producto_id_seq;
       public          postgres    false    216            5           0    0    producto_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.producto_id_seq OWNED BY public.producto.id;
          public          postgres    false    215            �           2604    41277 
   carrito id    DEFAULT     h   ALTER TABLE ONLY public.carrito ALTER COLUMN id SET DEFAULT nextval('public.carrito_id_seq'::regclass);
 9   ALTER TABLE public.carrito ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218            �           2604    41290    carrito_producto id    DEFAULT     z   ALTER TABLE ONLY public.carrito_producto ALTER COLUMN id SET DEFAULT nextval('public.carrito_producto_id_seq'::regclass);
 B   ALTER TABLE public.carrito_producto ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219    220            �           2604    41315    cupon id    DEFAULT     d   ALTER TABLE ONLY public.cupon ALTER COLUMN id SET DEFAULT nextval('public.cupon_id_seq'::regclass);
 7   ALTER TABLE public.cupon ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    221    222            �           2604    41325    producto id    DEFAULT     j   ALTER TABLE ONLY public.producto ALTER COLUMN id SET DEFAULT nextval('public.producto_id_seq'::regclass);
 :   ALTER TABLE public.producto ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    216    216            &          0    41243    carrito 
   TABLE DATA                 public          postgres    false    218   �%       (          0    41251    carrito_producto 
   TABLE DATA                 public          postgres    false    220   !&       *          0    41268    cupon 
   TABLE DATA                 public          postgres    false    222   �&       $          0    41236    producto 
   TABLE DATA                 public          postgres    false    216   �&       6           0    0    carrito_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.carrito_id_seq', 1, true);
          public          postgres    false    217            7           0    0    carrito_producto_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.carrito_producto_id_seq', 5, true);
          public          postgres    false    219            8           0    0    cupon_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.cupon_id_seq', 3, true);
          public          postgres    false    221            9           0    0    producto_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.producto_id_seq', 5, true);
          public          postgres    false    215            �           2606    41279    carrito carrito_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.carrito
    ADD CONSTRAINT carrito_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.carrito DROP CONSTRAINT carrito_pkey;
       public            postgres    false    218            �           2606    41292 &   carrito_producto carrito_producto_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.carrito_producto
    ADD CONSTRAINT carrito_producto_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.carrito_producto DROP CONSTRAINT carrito_producto_pkey;
       public            postgres    false    220            �           2606    41324    cupon cupon_codigo_key 
   CONSTRAINT     S   ALTER TABLE ONLY public.cupon
    ADD CONSTRAINT cupon_codigo_key UNIQUE (codigo);
 @   ALTER TABLE ONLY public.cupon DROP CONSTRAINT cupon_codigo_key;
       public            postgres    false    222            �           2606    41317    cupon cupon_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.cupon
    ADD CONSTRAINT cupon_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.cupon DROP CONSTRAINT cupon_pkey;
       public            postgres    false    222            �           2606    41327    producto producto_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.producto DROP CONSTRAINT producto_pkey;
       public            postgres    false    216            �           2606    41297 1   carrito_producto carrito_producto_carrito_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.carrito_producto
    ADD CONSTRAINT carrito_producto_carrito_id_fkey FOREIGN KEY (carrito_id) REFERENCES public.carrito(id) ON DELETE CASCADE;
 [   ALTER TABLE ONLY public.carrito_producto DROP CONSTRAINT carrito_producto_carrito_id_fkey;
       public          postgres    false    3467    218    220            �           2606    41328 2   carrito_producto carrito_producto_producto_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.carrito_producto
    ADD CONSTRAINT carrito_producto_producto_id_fkey FOREIGN KEY (producto_id) REFERENCES public.producto(id) ON DELETE CASCADE;
 \   ALTER TABLE ONLY public.carrito_producto DROP CONSTRAINT carrito_producto_producto_id_fkey;
       public          postgres    false    220    216    3465            &   :   x���v
Q���W((M��L�KN,*�,�Ws�	uV�0�Q0300�30д��� �	|      (   W   x���v
Q���W((M��L�KN,*�,ɏ/(�O)M.�Ws�	uV�0�Q� S3=Mk.OL0k7�Q02,�pq �%O      *   \   x���v
Q���W((M��L�K.-��Ss�	uV�0�QPwqv62P�Q02�30�QHK�)Nմ��$�����ِD�F0ͦ ͦ(��� �2      $   �   x���v
Q���W((M��L�+(�O)M.�Ws�	uV�0�QP�I,(�/P�Q0420�30д��$B�P�o~iq*P��)�����BR�sS�:MH�i�1/�$�d')�5ju,M9�6-?/���j/ .�V     